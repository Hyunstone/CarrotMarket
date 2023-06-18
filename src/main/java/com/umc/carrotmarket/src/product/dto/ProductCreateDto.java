package com.umc.carrotmarket.src.product.dto;

import com.umc.carrotmarket.src.product.domain.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductCreateDto {
    private String sellerId;
    private String name;
    private String contents;
    private String home;

    public Product toProduct() {
        return Product.builder()
                .sellerId(sellerId)
                .buyerId(null)
                .name(name)
                .createAt(String.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .clicks(0)
                .contents(contents)
                .status("VALID")
                .home(home)
                .build();
    }
}
