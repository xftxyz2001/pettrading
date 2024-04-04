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
        Map<String, Object> map = new HashMap<>();
        User user;
        if (telphone != null) {
            map.put("telphone", telphone);
            user = userService.queryUser(map);
            map.remove("telphone");
            if (user != null) {
                MapHelper.failAndMessage(map, "手机号已经被注册");
            } else {
                MapHelper.successAndMessage(map, "可进行下一步");
            }
        } else {
            map.put("idcard", idcard);
            user = userService.queryUser(map);
            map.remove("idcard");
            if (user != null) {
                MapHelper.failAndMessage(map, "该身份证号已被注册");
            } else {
                MapHelper.successAndMessage(map, "验证完成，可进行注册");
            }
        }
        return map;
    }

    //注册账号
    @PostMapping("/register")
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        user.setAvatar(Constants.DEFAULT_AVATAR);
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
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("telphone", username);
        map.put("email", username);
        User user = userService.queryUser(map);
        map.remove("username");
        map.remove("telphone");
        map.remove("email");
        if (user == null) {
            MapHelper.failAndMessage(map, "用户不存在");
        } else if (password.equals(user.getPassword())) {
            map.put("uid", user.getUid());
            map.put("avatar", user.getAvatar());
            map.put("type", user.getType());
            MapHelper.successAndMessage(map, "欢迎，" + user.getUsername());
        } else {
            MapHelper.failAndMessage(map, "密码错误");
        }
        return map;
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
        Map<String, Object> map = new HashMap<>();
        User uuser = null;
        if (user.getTelphone() != null) {
            map.put("telphone", user.getTelphone());
            uuser = userService.queryUser(map);
            map.remove("telphone");
            if (uuser != null && !Objects.equals(uuser.getUid(), user.getUid())) {
                MapHelper.failAndMessage(map, "该手机号已被注册");
                return map;
            }
        }
        if (user.getUsername() != null) {
            map.put("username", user.getUsername());
            uuser = userService.queryUser(map);
            map.remove("username");
        }
        if (uuser == null || Objects.equals(uuser.getUid(), user.getUid())) {
            if (file != null) {
                if (!file.isEmpty()) {
                    String filename = fileService.saveFile(file, "avatar");
                    //存入头像地址
                    user.setAvatar(filename);
                    //查找旧的用户信息
                    map.put("uid", user.getUid());
                    uuser = userService.queryUser(map);
                    map.remove("uid");
                    String oldavatar = uuser.getAvatar();
                    //判断旧头像是否为默认头像，不是则删除旧头像
                    if (!oldavatar.startsWith("defaultavatar/")) {
                        fileService.deleteFile(oldavatar);
                    }
                }
            }
            userService.updateUser(user);
            map.put("avatar", user.getAvatar());
            MapHelper.successAndMessage(map, "修改成功");
        } else {
            MapHelper.failAndMessage(map, "用户名已存在");
        }
        return map;
    }

    //修改密码
    @PostMapping("/changepassword")
    public Map<String, Object> changepsaaword(User user, @RequestParam(value = "oldpassword") String oldpassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        User queryUser = userService.queryUser(map);
        map.remove("uid");
        if (queryUser.getPassword().equals(oldpassword)) {
            userService.updateUser(user);
            MapHelper.successAndMessage(map, "修改成功");
        } else {
            MapHelper.failAndMessage(map, "旧密码错误");
        }
        return map;
    }

    //删除用户
    @GetMapping("/deleteUser")
    public String deleteUser(Long uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        userService.deleteUser(map);
        petService.deletePet(map);
        addressService.deleteAddress(map);
        map.remove("uid");
        map.put("fromuid", uid);
        contactService.deleteContact(map);
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
