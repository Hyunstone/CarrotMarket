package com.umc.carrotmarket.src.review;

import com.umc.carrotmarket.src.review.domain.Review;
import com.umc.carrotmarket.src.review.dto.CreateReviewDto;
import com.umc.carrotmarket.src.review.dto.UpdateReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewDao reviewDao;
    @Override
    public void create(CreateReviewDto createReviewDto) {
        reviewDao.createReview(createReviewDto.toReview());
    }

    @Override
    public List<Review> getReviewList(Long productIdx) {
        return reviewDao.getReviewList(productIdx);
    }

    @Override
    public void updateReview(UpdateReviewDto updateReviewDto) {
        reviewDao.updateReview(updateReviewDto);
    }

    @Override
    public void deleteReview(Long productIdx) {
        reviewDao.deleteReview(productIdx);
    }
}
