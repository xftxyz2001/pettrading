package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private Long cid;
    private Long fromuid;//当前登录的用户id
    private Long touid;//当前用户联系过的人
    private Integer noread;//未读消息数
}
