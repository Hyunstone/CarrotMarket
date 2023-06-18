package com.umc.carrotmarket.src.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Review {
    private Long idx;
    private String buyerId;
    private String sellerId;
    private String contents;
    private String createAt;
    private String updateAt; // nullable
    private String status;
}
