package com.example.controller;

import com.example.domain.Pet;
import com.example.domain.Petorder;
import com.example.service.PetService;
import com.example.service.PetorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/petorder")
public class PetorderController {

    private final PetorderService petorderService;
    private final PetService petService;

    @PostMapping("/addpetorder")
    public Map<String, Object> addpetorder(Petorder petorder) {
        Pet spet = petService.queryPetById(petorder.getPid());
        Map<String, Object> map = new HashMap<>();
        if (spet == null) {
            map.put("flag", 0);
            map.put("msg", "卖方已删除该宠物");
        } else {
            if (spet.getPk() == 0) {
                Date date = new Date();
                petorder.setDate(date);
                Pet pet = new Pet();
                pet.setPid(petorder.getPid());
                pet.setPk(1);
                petService.updatePet(pet);
                petorderService.addpetorder(petorder);
                map.put("flag", 1);
                map.put("msg", "订单提交成功");
            } else {
                map.put("flag", 0);
                map.put("msg", "该宠物已被人购买");
            }
        }
        return map;
    }

    @GetMapping("/deletepetorder")
    public String deletepetorder(@RequestParam(name = "poid", required = false) Long poid, @RequestParam(name = "uid", required = false) Long uid) {
        Map<String, Object> map = new HashMap<>();
        if (poid != null) {
            map.put("poid", poid);
        }
        if (uid != null) {
            map.put("uid", uid);
        }
        petorderService.deletepetorder(map);
        return "删除成功";
    }

    @PostMapping("/updatepetorder")
    public String updatepetorder(Petorder petorder, @RequestParam(name = "pk", required = false) Integer pk) {
        Date date = new Date();
        petorder.setDate(date);
        petorderService.updatepetorder(petorder);
        if (pk != null) {
            Pet pet = new Pet();
            pet.setPid(petorder.getPid());
            pet.setPk(pk);
            petService.updatePet(pet);
        }
        if (petorder.getPostatu() == 3) {
            return "订单修改中，等待卖家确认";
        } else if (petorder.getPostatu() == 0) {
            return "订单已修改成功";
        } else if (petorder.getPostatu() == 2) {
            return "订单已取消";
        } else if (petorder.getPostatu() == 4) {
            return "订单正在取消，等待卖方确认";
        } else {
            return "订单已完成";
        }
    }

    @GetMapping("/querypetorder")
    public List<Petorder> querypetorder(
            @RequestParam(name = "uid", required = false) Long uid,
            @RequestParam(name = "pid", required = false) Long pid,
            @RequestParam(name = "poid", required = false) Long poid,
            @RequestParam(name = "recipientname", required = false) String recipientname,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "postatu", required = false) Integer postatu,
            @RequestParam(name = "date", required = false) String date) {
        Map<String, Object> map = warpQueryMap(uid, pid, poid, recipientname, address, phone, postatu, date);
        return petorderService.querypetorder(map);
    }

    @GetMapping("/querypetorderpage")
    public Map<String, Object> querypetorderpage(@RequestParam(name = "page", required = false) Integer page,
                                                 @RequestParam(name = "count", required = false) Integer count,
                                                 @RequestParam(name = "uid", required = false) Long uid,
                                                 @RequestParam(name = "pid", required = false) Long pid,
                                                 @RequestParam(name = "poid", required = false) Long poid,
                                                 @RequestParam(name = "recipientname", required = false) String recipientname,
                                                 @RequestParam(name = "address", required = false) String address,
                                                 @RequestParam(name = "phone", required = false) String phone,
                                                 @RequestParam(name = "postatu", required = false) Integer postatu,
                                                 @RequestParam(name = "date", required = false) String date) {
        Map<String, Object> map = warpQueryMap(uid, pid, poid, recipientname, address, phone, postatu, date);
        if (page != null && count != null) {
            map.put("min", (page - 1) * count);
            map.put("max", count);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("total", petorderService.countpetorder(map));
        res.put("petorder", petorderService.querypetorderpage(map));
        return res;
    }


    private Map<String, Object> warpQueryMap(Long uid, Long pid, Long poid, String recipientname, String address, String phone, Integer postatu, String date) {
        Map<String, Object> map = new HashMap<>();
        if (uid != null) {
            map.put("uid", uid);
        }
        if (pid != null) {
            map.put("pid", pid);
        }
        if (poid != null) {
            map.put("poid", poid);
        }
        if (StringUtils.hasLength(recipientname)) {
            map.put("recipientname", recipientname);
        }
        if (StringUtils.hasLength(address)) {
            map.put("address", address);
        }
        if (StringUtils.hasLength(phone)) {
            map.put("phone", phone);
        }
        if (postatu != null) {
            map.put("postatu", postatu);
        }
        if (StringUtils.hasLength(date)) {
            map.put("date", date);
        }
        return map;
    }
}
