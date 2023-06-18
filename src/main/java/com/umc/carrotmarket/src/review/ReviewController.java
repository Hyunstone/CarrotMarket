package com.umc.carrotmarket.src.review;

import com.umc.carrotmarket.src.review.domain.Review;
import com.umc.carrotmarket.src.review.dto.CreateReviewDto;
import com.umc.carrotmarket.src.review.dto.UpdateReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Object> createReview(@RequestBody CreateReviewDto createReview) {
        reviewService.create(createReview);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getReviewList(@PathVariable Long productIdx) {
        return new ResponseEntity<>(reviewService.getReviewList(productIdx), HttpStatus.OK);
    }

    @PostMapping("/review/{idx}/update")
    public ResponseEntity<Object> updateReview(@RequestBody UpdateReviewDto updateReviewdto) {
        reviewService.updateReview(updateReviewdto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/review/{idx}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long productIdx) {
        reviewService.deleteReview(productIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
