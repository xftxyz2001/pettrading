package com.example.controller;

import com.example.domain.BKind;
import com.example.service.BKindService;
import com.example.service.PetService;
import com.example.service.SKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bkind")
public class BKindController {

    private final BKindService bKindService;
    private final PetService petService;
    private final SKindService sKindService;

    @PostMapping("/addBKind")
    public Map<String, Object> addBKind(BKind bkind) {
        bKindService.addBKind(bkind);
        Map<String, Object> map = new HashMap<>();
        map.put("bkid", bkind.getBkid());
        map.put("msg", "类型添加成功");
        return map;
    }

    @GetMapping("/deleteBKind")
    public String deleteBKind(Long bkid) {
        Map<String, Object> map = new HashMap<>();
        petService.setPetbkidNULL(bkid);
        map.put("bkid", bkid);
        sKindService.deleteSKind(map);
        bKindService.deleteBKind(bkid);
        return "类型删除成功";
    }

    @GetMapping("/updateBKind")
    public String updateBKind(BKind kind) {
        bKindService.updateBKind(kind);
        return "类型修改成功";
    }

    @GetMapping("/queryBKind")
    public List<BKind> queryBKind() {
        return bKindService.queryBKind();
    }

    @GetMapping("/queryBKindname")
    public List<Map<String, Object>> queryBKindname() {
        return bKindService.queryBKindname();
    }

    @GetMapping("/queryBKindandSKind")
    public List<BKind> queryBKindandSKind(BKind bkind) {
        Map<String, Object> map = new HashMap<>();
        if (bkind.getBkid() != null) {
            map.put("bkid", bkind.getBkid());
        }
        if (StringUtils.hasLength(bkind.getBkindname())) {
            map.put("bkindname", bkind.getBkindname());
        }
        return bKindService.queryBKindandSKind(map);
    }
}
