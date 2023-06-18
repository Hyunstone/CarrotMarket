package com.umc.carrotmarket.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordUpdateDto {
    private String origin;
    private String change;
}
