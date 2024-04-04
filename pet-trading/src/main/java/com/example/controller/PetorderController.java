package com.example.controller;

import com.example.domain.Pet;
import com.example.domain.Petorder;
import com.example.helper.MapHelper;
import com.example.service.PetService;
import com.example.service.PetorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/petorder")
public class PetorderController {

    private final PetorderService petorderService;
    private final PetService petService;

    @PostMapping("/addpetorder")
    public Map<String, Object> addpetorder(Petorder petorder) {
        Pet spet = petService.queryPetById(petorder.getPid());
        Map<String, Object> map = new HashMap<>();
        if (spet == null) {
            MapHelper.failAndMessage(map, "卖方已删除该宠物");
        } else {
            if (spet.getPk() == Pet.PK_SELLING) {
                petorder.setDate(new Date());
                Pet pet = new Pet();
                pet.setPid(petorder.getPid());
                pet.setPk(Pet.PK_SOLD);
                petService.updatePet(pet);
                petorderService.addpetorder(petorder);
                MapHelper.successAndMessage(map, "订单提交成功");
            } else {
                MapHelper.failAndMessage(map, "该宠物已被人购买");
            }
        }
        return map;
    }

    @GetMapping("/deletepetorder")
    public String deletepetorder(@RequestParam(name = "poid", required = false) Long poid,
                                 @RequestParam(name = "uid", required = false) Long uid) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "poid", poid);
        MapHelper.putIfNotNull(map, "uid", uid);
        petorderService.deletepetorder(map);
        return "删除成功";
    }

    @PostMapping("/updatepetorder")
    public String updatepetorder(Petorder petorder, @RequestParam(name = "pk", required = false) Integer pk) {
        petorder.setDate(new Date());
        petorderService.updatepetorder(petorder);
        if (pk != null) {
            Pet pet = new Pet();
            pet.setPid(petorder.getPid());
            pet.setPk(pk);
            petService.updatePet(pet);
        }
        if (petorder.getPostatu() == Petorder.POSTATU_MODIFYING) {
            return "订单修改中，等待卖家确认";
        } else if (petorder.getPostatu() == Petorder.POSTATU_ONGOING) {
            return "订单已修改成功";
        } else if (petorder.getPostatu() == Petorder.POSTATU_CANCELED) {
            return "订单已取消";
        } else if (petorder.getPostatu() == Petorder.POSTATU_CANCELING) {
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
        MapHelper.putPagination(map, page, count);
        Map<String, Object> res = new HashMap<>();
        res.put("total", petorderService.countpetorder(map));
        res.put("petorder", petorderService.querypetorderpage(map));
        return res;
    }


    private Map<String, Object> warpQueryMap(Long uid, Long pid, Long poid, String recipientname, String address,
                                             String phone, Integer postatu, String date) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "uid", uid);
        MapHelper.putIfNotNull(map, "pid", pid);
        MapHelper.putIfNotNull(map, "poid", poid);
        MapHelper.putIfHasLength(map, "recipientname", recipientname);
        MapHelper.putIfHasLength(map, "address", address);
        MapHelper.putIfHasLength(map, "phone", phone);
        MapHelper.putIfNotNull(map, "postatu", postatu);
        MapHelper.putIfHasLength(map, "date", date);
        return map;
    }
}
