package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKind {

    private Long skid;
    private String savatar;
    private String skindname;
    private Long bkid;//对应种类的bkid

    private BKind bkind;

    private List<Pet> pet;
}
