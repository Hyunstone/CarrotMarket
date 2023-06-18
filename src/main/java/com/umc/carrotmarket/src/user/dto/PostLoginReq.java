package com.umc.carrotmarket.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostLoginReq {
    private Long userIdx;
    private String jwt;
}
