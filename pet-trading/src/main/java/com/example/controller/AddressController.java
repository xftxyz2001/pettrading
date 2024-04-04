package com.example.controller;

import com.example.domain.Address;
import com.example.helper.MapHelper;
import com.example.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/addAddress")
    public Map<String, Object> addAddress(Address address) {
        addressService.addAddress(address);
        Map<String, Object> map = new HashMap<>();
        map.put("aid", address.getAid());
        map.put("msg", "添加成功");
        return map;
    }

    @GetMapping("/deleteAddress")
    public String deleteAddress(@RequestParam(name = "aid", required = false) Long aid,
                                @RequestParam(name = "uid", required = false) String uid) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "aid", aid);
        MapHelper.putIfNotNull(map, "uid", uid);
        addressService.deleteAddress(map);
        return "改地址已被删除";
    }

    @PostMapping("/updateAddress")
    public String updateAddress(Address address) {
        addressService.updateAddress(address);
        return "修改成功";
    }

    @GetMapping("/queryAddressbyuid")
    public List<Address> queryAddressbyuid(Long uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        return addressService.queryAddressbyuid(map);
    }

    @GetMapping("/queryaddress")
    public Map<String, Object> queryaddress(@RequestParam(name = "page", required = false) Integer page,
                                            @RequestParam(name = "count", required = false) Integer count,
                                            Address address) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "aid", address.getAid());
        MapHelper.putIfNotNull(map, "uid", address.getUid());
        MapHelper.putIfHasLength(map, "address", address.getAddress());
        MapHelper.putIfHasLength(map, "phone", address.getPhone());
        MapHelper.putIfHasLength(map, "username", address.getUsername());
        MapHelper.putPagination(map, page, count);
        Map<String, Object> res = new HashMap<>();
        res.put("total", addressService.countaddress(map));
        res.put("address", addressService.queryAddress(map));
        return res;
    }
}
