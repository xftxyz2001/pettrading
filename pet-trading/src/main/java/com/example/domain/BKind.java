package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BKind {

    private Long bkid;
    private String bavatar;
    private String bkindname;

    private List<Pet> pet;

    private List<SKind> skind;
}
