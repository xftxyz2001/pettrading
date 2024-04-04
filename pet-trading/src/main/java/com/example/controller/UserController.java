package com.example.controller;

import com.example.config.Constants;
import com.example.domain.ContactUser;
import com.example.domain.User;
import com.example.helper.MapHelper;
import com.example.helper.PrivacyHelper;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PetService petService;
    private final AddressService addressService;
    private final ContactService contactService;
    private final FileService fileService;

    //注册前检验用户是否存在
    @GetMapping("/checkuser")
    public Map<String, Object> checkuser(@RequestParam(value = "telphone", required = false) String telphone,
                                         @RequestParam(value = "idcard", required = false) String idcard) {
        Map<String, Object> queryUserParam = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        User user;
        if (telphone != null) {
            queryUserParam.put("telphone", telphone);
            user = userService.queryUser(queryUserParam);
            if (user != null) {
                MapHelper.failAndMessage(res, "手机号已经被注册");
            } else {
                MapHelper.successAndMessage(res, "可进行下一步");
            }
        } else {
            queryUserParam.put("idcard", idcard);
            user = userService.queryUser(queryUserParam);
            if (user != null) {
                MapHelper.failAndMessage(res, "该身份证号已被注册");
            } else {
                MapHelper.successAndMessage(res, "验证完成，可进行注册");
            }
        }
        return res;
    }

    //注册账号
    @PostMapping("/register")
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        user.setAvatar(Constants.DEFAULT_AVATAR1);
        user.setSex("保密");
        user.setType(User.TYPE_USER);
        userService.addUser(user);
        MapHelper.successAndMessage(map, "注册成功");
        return map;
    }

    //登录
    @GetMapping("/login")
    public Map<String, Object> login(String username, String password) {
        //查找该用户名(即该账户)是否存在
        Map<String, Object> queryUserParam = new HashMap<>();
        queryUserParam.put("username", username);
        queryUserParam.put("telphone", username);
        queryUserParam.put("email", username);
        User user = userService.queryUser(queryUserParam);

        Map<String, Object> res = new HashMap<>();
        if (user == null) {
            MapHelper.failAndMessage(res, "用户不存在");
        } else if (password.equals(user.getPassword())) {
            res.put("uid", user.getUid());
            res.put("avatar", user.getAvatar());
            res.put("type", user.getType());
            MapHelper.successAndMessage(res, "欢迎，" + user.getUsername());
        } else {
            MapHelper.failAndMessage(res, "密码错误");
        }
        return res;
    }

    //查找单个用户的信息
    @GetMapping("/queryuser")
    public User queryuser(Long uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        User user = userService.queryUser(map);
        // 如果是普通用户，隐藏身份证号
        if (user.getType() == User.TYPE_USER) {
            user.setIdcard(PrivacyHelper.idcard(user.getIdcard()));
        }
        return user;
    }

    //修改用户信息
    @PostMapping("/updateuser")
    public Map<String, Object> updateuser(@RequestParam(name = "file", required = false) MultipartFile file,
                                          User user) throws IOException {
        Map<String, Object> res = new HashMap<>();
        User uuser = null;
        // 根据手机号查询，检查手机号是否已经被注册
        if (user.getTelphone() != null) {
            Map<String, Object> mapTel = new HashMap<>();
            mapTel.put("telphone", user.getTelphone());
            uuser = userService.queryUser(mapTel);
        }
        if (uuser != null && !Objects.equals(uuser.getUid(), user.getUid())) {
            MapHelper.failAndMessage(res, "该手机号已被注册");
            return res;
        }
        // 根据用户名查询，检查用户名是否已存在
        if (user.getUsername() != null) {
            Map<String, Object> mapUname = new HashMap<>();
            mapUname.put("username", user.getUsername());
            uuser = userService.queryUser(mapUname);
        }
        if (uuser != null && !Objects.equals(uuser.getUid(), user.getUid())) {
            MapHelper.failAndMessage(res, "用户名已存在");
            return res;
        }
        // 根据UID查询
        Map<String, Object> mapUID = new HashMap<>();
        mapUID.put("uid", user.getUid());
        uuser = userService.queryUser(mapUID);
        if (file != null) {
            if (!file.isEmpty()) {
                String filename = fileService.saveFile(file, "avatar");
                user.setAvatar(filename);
                String oldavatar = uuser.getAvatar();
                //判断旧头像是否为默认头像，不是则删除旧头像
                if (!oldavatar.startsWith(Constants.DEFAULT_AVATAR_PREFIX)) {
                    fileService.deleteFile(oldavatar);
                }
            }
        }
        userService.updateUser(user);
        res.put("avatar", user.getAvatar());
        MapHelper.successAndMessage(res, "修改成功");
        return res;
    }

    //修改密码
    @PostMapping("/changepassword")
    public Map<String, Object> changepsaaword(User user, @RequestParam(value = "oldpassword") String oldpassword) {
        Map<String, Object> queryUserParam = new HashMap<>();
        queryUserParam.put("uid", user.getUid());
        User queryUser = userService.queryUser(queryUserParam);

        Map<String, Object> res = new HashMap<>();
        if (queryUser.getPassword().equals(oldpassword)) {
            userService.updateUser(user);
            MapHelper.successAndMessage(res, "修改成功");
        } else {
            MapHelper.failAndMessage(res, "旧密码错误");
        }
        return res;
    }

    //删除用户
    @GetMapping("/deleteUser")
    public String deleteUser(Long uid) {
        Map<String, Object> mapUID = new HashMap<>();
        mapUID.put("uid", uid);
        userService.deleteUser(mapUID);
        petService.deletePet(mapUID);
        addressService.deleteAddress(mapUID);

        Map<String, Object> mapFROMUID = new HashMap<>();
        mapFROMUID.put("fromuid", uid);
        contactService.deleteContact(mapFROMUID);
        return "该用户已被删除";
    }

    //查找所有用户
    @GetMapping("/queryalluser")
    public List<User> querAllUser(@RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "count", required = false) Integer count) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putPagination(map, page, count);
        return userService.queryAllUser(map);
    }

    @GetMapping("/countuser")
    public int countuser() {
        Map<String, Object> map = new HashMap<>();
        return userService.countuser(map);
    }

    @GetMapping("/querycontactuser")
    public List<ContactUser> querycontactuser(Long uid) {
        return userService.querycontactuser(uid);
    }

    @GetMapping("/queryuserby")
    public Map<String, Object> queryuserby(User user) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "uid", user.getUid());
        MapHelper.putIfHasLength(map, "username", user.getUsername());
        MapHelper.putIfHasLength(map, "realname", user.getRealname());
        MapHelper.putIfHasLength(map, "idcard", user.getIdcard());
        MapHelper.putIfHasLength(map, "telphone", user.getTelphone());
        Map<String, Object> res = new HashMap<>();
        res.put("total", userService.countuser(map));
        res.put("user", userService.queryuserby(map));
        return res;
    }
}
