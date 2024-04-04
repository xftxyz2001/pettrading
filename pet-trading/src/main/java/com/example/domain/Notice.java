package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    //类型  0-普通消息，1-提交订单，2-修改订单，3-取消订单，4-完成订单，5-确认修改订单，6-确认删除订单,7-帮助求购
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SUBMIT_ORDER = 1;
    public static final int TYPE_MODIFY_ORDER = 2;
    public static final int TYPE_CANCEL_ORDER = 3;
    public static final int TYPE_FINISH_ORDER = 4;
    public static final int TYPE_CONFIRM_MODIFY_ORDER = 5;
    public static final int TYPE_CONFIRM_DELETE_ORDER = 6;
    public static final int TYPE_HELP_BUY = 7;

    // 是否已读 0-未读，1-已读
    public static final int ISREAD_UNREAD = 0;
    public static final int ISREAD_READ = 1;

    private Long nid;
    private Integer type;//类型  0-普通消息，1-提交订单，2-修改订单，3-取消订单，4-完成订单，5-确认修改订单，6-确认删除订单,7-帮助求购
    private Long fromuid;//发送者
    private String fromusername;//发送者名字
    private String fromavatar;//发送者头像
    private Long touid;//接收者
    private String tousername;//发送者名字
    private String msg;//消息
    private Long pid;//订单的宠物id
    private Date date;//发送时间
    private Integer isread;//是否已读 0-未读，1-已读
}
