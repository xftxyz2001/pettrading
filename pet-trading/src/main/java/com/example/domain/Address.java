package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long aid;
    private String phone;
    private String address;
    private String username;
    private Long uid;

    private User user;
}
