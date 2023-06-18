package com.umc.carrotmarket.src.review.dto;

import com.umc.carrotmarket.src.review.domain.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReviewDto {
    private String buyerId;
    private String sellerId; // nullable
    private String contents;

    public Review toReview() {
        return Review.builder()
                .buyerId(buyerId)
                .sellerId(sellerId)
                .contents(contents)
                .sellerId(String.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .status("VALID")
                .build();
    }

}
