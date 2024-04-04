package com.example.controller;

import com.example.domain.SKind;
import com.example.helper.MapHelper;
import com.example.service.SKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/skind")
public class SKindController {

    private final SKindService sKindService;

    @PostMapping("/addskind")
    public Map<String, Object> addSKind(SKind skind) {
        sKindService.addSKind(skind);
        Map<String, Object> map = new HashMap<>();
        map.put("skid", skind.getSkid());
        map.put("msg", "种类添加成功");
        return map;
    }

    @GetMapping("/deleteskind")
    public String deleteSKind(Long skid) {
        Map<String, Object> map = new HashMap<>();
        map.put("skid", skid);
        sKindService.deleteSKind(map);
        return "品种删除成功";
    }

    @PostMapping("/updateskind")
    public String updateSKind(SKind skind) {
        sKindService.updateSKind(skind);
        return "种类修改成功";
    }

    @GetMapping("/queryallskind")
    public List<SKind> queryAllSKind(SKind sKind) {
        Map<String, Object> map = new HashMap<>();
        MapHelper.putIfNotNull(map, "bkid", sKind.getBkid());
        MapHelper.putIfNotNull(map, "skid", sKind.getSkid());
        MapHelper.putIfHasLength(map, "skindname", sKind.getSkindname());
        return sKindService.queryAllSKind(map);
    }
}
