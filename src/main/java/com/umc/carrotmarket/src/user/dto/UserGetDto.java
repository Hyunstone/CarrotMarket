package com.umc.carrotmarket.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserGetDto {

    private int idx;
    private String name;
    private String userId;
    private String password;
    private String profile_image;
    private String home;

}
