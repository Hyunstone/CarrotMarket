package com.umc.carrotmarket.src.review;

import com.umc.carrotmarket.src.review.domain.Review;
import com.umc.carrotmarket.src.review.dto.CreateReviewDto;
import com.umc.carrotmarket.src.review.dto.UpdateReviewDto;

import java.util.List;

public interface ReviewService {
    void create(CreateReviewDto createReviewDto);

    List<Review> getReviewList(Long productIdx);

    void updateReview(UpdateReviewDto updateReviewDto);

    void deleteReview(Long productIdx);
}
