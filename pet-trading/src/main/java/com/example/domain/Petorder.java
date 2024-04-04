package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Petorder {

    // 0-进行中，1-已完成，2-已取消，3-修改中，4-取消中
    public static final int POSTATU_ONGOING = 0;
    public static final int POSTATU_FINISHED = 1;
    public static final int POSTATU_CANCELED = 2;
    public static final int POSTATU_MODIFYING = 3;
    public static final int POSTATU_CANCELING = 4;

    private Long poid;
    private Long uid;//买家uid
    private Long pid;//订单宠物id
    private Long puid;//卖家uid
    private String phone;
    private String address;
    private String recipientname;
    private String newphone;
    private String newaddress;
    private String newrecipientname;
    private Date date;
    private Double price;//下单时的宠物价格
    private Integer postatu;//0-进行中，1-已完成，2-已取消，3-修改中，4-取消中

    private User user;
    private Pet pet;
}
