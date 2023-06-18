package com.umc.carrotmarket.src.product.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
        private Long idx;
        private String sellerId;
        private String buyerId; // nullable
        private String name;
        private String createAt;
        private String updateAt; // nullable
        private int clicks;
        private String contents;
        private String status;
        private String home;

}
