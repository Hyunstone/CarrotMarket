package com.umc.carrotmarket.src.product.dto;

import lombok.Data;

@Data
public class ProductUpdateDto {
    private Long idx;
    private String name;
    private String contents;
}
