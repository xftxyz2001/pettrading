package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // 0-管理员，1-普通用户
    public static final int TYPE_ADMIN = 0;
    public static final int TYPE_USER = 1;

    private Long uid;//用户id
    private String avatar;//头像
    private Integer type;//0-管理员，1-普通用户
    private String username;//用户名
    private String realname;//真实姓名
    private String idcard;//身份证号
    private String sex;//性别
    private String password;//密码
    private String telphone;//联系电话
    private String email;//邮箱
    private String wechat;//微信
//    private String address;//收货地址

    private List<Pet> pet;//用户上传的宠物

    private List<Address> address;//收货地址

    private List<Petorder> petorder;

//    @Temporal(TemporalType.TIMESTAMP) // 是用来定义日期类型
//    private Date birthday;//生日
}
