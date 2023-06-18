package com.umc.carrotmarket.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDto {
    private String name;
    private String profile_image;
    private String home;
}
