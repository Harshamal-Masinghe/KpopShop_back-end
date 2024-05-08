package com.kpopshop.review.controller;

import com.kpopshop.review.model.Review;
import com.kpopshop.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@RequestBody Review review){
        return reviewService.createReview(review);
    }

    @GetMapping
    public List<Review> getReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable String reviewId){
        return reviewService.getReviewById(reviewId);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReviewById(@PathVariable String reviewId, @RequestBody Review updatedReview) {
        Optional<Review> optionalReview = reviewService.updateReviewById(reviewId, updatedReview);
        return optionalReview.map(review -> new ResponseEntity<>(review, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable String reviewId){
        return reviewService.deleteReview(reviewId);
    }

}
