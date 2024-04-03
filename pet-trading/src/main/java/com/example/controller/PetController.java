package com.example.controller;

import com.example.config.Constants;
import com.example.domain.Pet;
import com.example.domain.Petorder;
import com.example.domain.Photo;
import com.example.service.FileService;
import com.example.service.PetService;
import com.example.service.PetorderService;
import com.example.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final PhotoService photoService;
    private final PetorderService petorderService;
    private final FileService fileService;

    //发布宠物出售或购买请求
    @PostMapping("/addPet")
    public String addPet(@RequestParam(name = "files", required = false) MultipartFile[] files, Pet pet) throws IOException {
        Date date = new Date();
        pet.setDate(date);
        petService.addPet(pet);
        Photo photo = new Photo();
        photo.setPid(pet.getPid());
        if (!ObjectUtils.isEmpty(files)) {
            for (MultipartFile file : files) {
                String url = fileService.saveFile(file, "petimg");
                photo.setUrl(url);
                photoService.addPhoto(photo);
            }
        } else {
            photo.setUrl(Constants.DEFAULT_PET_IMAGE);
            photoService.addPhoto(photo);
        }
        return "发布成功";
    }

    //删除宠物
    @GetMapping("/deletePet")
    public String deletePet(@RequestParam(name = "uid", required = false) Long uid, @RequestParam(name = "pid", required = false) Long pid) {
        Map<String, Object> map = new HashMap<>();
        if (uid != null) {
            map.put("uid", uid);
        } else {
            map.put("pid", pid);
            map.put("postatu", 0);
        }
        List<Petorder> petorder = petorderService.querypetorder(map);
        map.remove("postatu");
        if (petorder.isEmpty()) {
            petService.deletePet(map);
            photoService.deletePhoto(map);
            return "删除成功";
        } else {
            return "该宠物已被购买，无法删除";
        }
    }

    //更新宠物信息
    @PostMapping("/updatePet")
    public String updatePet(@RequestParam(name = "files", required = false) MultipartFile[] files, @RequestParam(name = "ppid", required = false) Long[] ppid, Pet pet) throws IOException {
        Date date = new Date();
        pet.setDate(date);
        Photo photo = new Photo();
        photo.setPid(pet.getPid());
        //删除图片
        if (!ObjectUtils.isEmpty(files)) {
            Map<String, Object> photomap = new HashMap<>();
            for (Long aLong : ppid) {
                photomap.put("ppid", aLong);
                photoService.deletePhoto(photomap);
                photomap.remove("ppid");
            }
        }
        //增加图片
        if (!ObjectUtils.isEmpty(files)) {
            for (MultipartFile file : files) {
                String url = fileService.saveFile(file, "petimg");
                photo.setUrl(url);
                photoService.addPhoto(photo);
            }
        }
        //宠物为求购时，若没有图片，增加无数据图片
        if (pet.getPk() == 3) {
            List<Photo> photos = photoService.queryPhotobypid(pet.getPid());
            if (photos.isEmpty()) {
                photo.setUrl(Constants.DEFAULT_PET_IMAGE);
                photoService.addPhoto(photo);
            }
        }
        petService.updatePet(pet);
        return "修改完成";
    }

    //根据id查询宠物
    @GetMapping("/querypetbyid")
    public Pet queryPetById(Long pid) {
        return petService.queryPetById(pid);
    }

    //根据名字模糊查询
    @GetMapping("/queryPetByName")
    public List<Pet> queryPetByName(String petname) {
        return petService.queryPetByName(petname);
    }

    //查询所有宠物
    @GetMapping("/queryAllPet")
    public List<Pet> queryAllPet(@RequestParam(name = "uid", required = false) Long uid, @RequestParam(name = "pk", required = false) Integer pk) {
        Map<String, Object> map = new HashMap<>();
        if (uid != null) {
            map.put("uid", uid);
        }
        if (pk != null) {
            map.put("pk", pk);
        }
        return petService.queryAllPet(map);
    }

    //分页查询宠物
    @GetMapping("/querypetpage")
    public List<Pet> querypetpage(int page, int count,
                                  @RequestParam(name = "pid", required = false) Integer pid,
                                  @RequestParam(name = "uid", required = false) Integer uid,
                                  @RequestParam(name = "pk", required = false) Integer pk,
                                  @RequestParam(name = "bkid", required = false) String bkid,
                                  @RequestParam(name = "skid", required = false) String skid,
                                  @RequestParam(name = "age", required = false) String age,
                                  @RequestParam(name = "maxprice", required = false) Double maxprice,
                                  @RequestParam(name = "price", required = false) Double price,
                                  @RequestParam(name = "petname", required = false) String petname,
                                  @RequestParam(name = "date", required = false) String date,
                                  @RequestParam(name = "sort", required = false) String sort) {
        Map<String, Object> map = warpQueryMap(pid, uid, pk, bkid, skid, age, price, date, petname);
        map.put("min", (page - 1) * count);
        map.put("max", count);
        if (maxprice != null) {
            map.put("maxprice", maxprice);
        }
        if (StringUtils.hasLength(sort)) {
            map.put("sort", sort);
        }
        return petService.queryPetpage(map);
    }

    //查询宠物条数
    @GetMapping("/querypetcount")
    public int querypetcount(@RequestParam(name = "pid", required = false) Integer pid,
                             @RequestParam(name = "uid", required = false) Integer uid,
                             @RequestParam(name = "pk", required = false) Integer pk,
                             @RequestParam(name = "bkid", required = false) String bkid,
                             @RequestParam(name = "skid", required = false) String skid,
                             @RequestParam(name = "age", required = false) String age,
                             @RequestParam(name = "price", required = false) Double price,
                             @RequestParam(name = "date", required = false) String date,
                             @RequestParam(name = "petname", required = false) String petname) {
        Map<String, Object> map = warpQueryMap(pid, uid, pk, bkid, skid, age, price, date, petname);
        return petService.querypetcount(map);
    }


    private Map<String, Object> warpQueryMap(Integer pid, Integer uid, Integer pk, String bkid, String skid, String age, Double price, String date, String petname) {
        Map<String, Object> map = new HashMap<>();
        if (pid != null) {
            map.put("pid", pid);
        }
        if (uid != null) {
            map.put("uid", uid);
        }
        if (pk != null) {
            map.put("pk", pk);
        }
        if (StringUtils.hasLength(skid)) {
            map.put("skid", skid);
        } else if (StringUtils.hasLength(bkid)) {
            map.put("bkid", bkid);
        }
        if (StringUtils.hasLength(age)) {
            map.put("age", age);
        }
        if (StringUtils.hasLength(petname)) {
            map.put("petname", petname);
        }
        if (price != null) {
            map.put("price", price);
        }
        if (StringUtils.hasLength(date)) {
            map.put("date", date);
        }
        return map;
    }

    //查询所有宠物的最高价
    @GetMapping("/queryMaxPrice")
    public String queryMaxPrice() {
        return petService.queryMaxPrice();
    }

    //查询所有宠物年龄
    @GetMapping("/queryage")
    public List<Map<String, Object>> queryage() {
        return petService.queryage();
    }
}
