package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    // 宠物发售类型（0-出售中，1-已出售，2-已完成出售，3-发布求购中）
    public static final int PK_SELLING = 0;
    public static final int PK_SOLD = 1;
    public static final int PK_FINISH = 2;
    public static final int PK_BUY = 3;

    private Long pid;//宠物id
    private String petname;//宠物标题
    private String sex;//宠物性别
    private Double price;//宠物价格
    private Integer age;//宠物年龄
    private String description;//宠物描述
    private Integer pk;//宠物发售类型（0-出售中，1-已出售，2-已完成出售，3-发布求购中）
    private Long uid;//对应买家或卖家的uid
    private Long bkid;//对应种类的bkid
    private Long skid;//对应品种的skid
    private Date date;

    private User user;//发布宠物的买家或卖家

    private BKind bkind;//宠物所属的类型

    private SKind skind;//宠物所属的品种

    private List<Photo> photo;//宠物图片,最多5张

    private List<Petorder> petorder;
}
