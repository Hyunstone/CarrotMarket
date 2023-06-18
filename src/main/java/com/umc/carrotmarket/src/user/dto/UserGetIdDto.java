package com.umc.carrotmarket.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetIdDto {
    private String name;
    // 테이블에 전화번호, 이메일 받는걸 까먹...
}
