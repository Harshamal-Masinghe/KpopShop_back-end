package com.kpopshop.review.service;

import com.kpopshop.review.model.Review;
import com.kpopshop.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review){
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getReviewById(String reviewId){
        if(reviewRepository.findById(reviewId).isPresent()){
            return reviewRepository.findById(reviewId).get();
        }
        else {
            return null;
        }
    }

    public Optional<Review> updateReviewById(String reviewId, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setName(updatedReview.getName());
            existingReview.setEmail(updatedReview.getEmail());
            existingReview.setComment(updatedReview.getComment());
            existingReview.setImages(updatedReview.getImages());
            existingReview.setRating(updatedReview.getRating());
            return Optional.of(reviewRepository.save(existingReview));
        }
        return Optional.empty();
    }

    public String deleteReview(String reviewId){
        reviewRepository.deleteById(reviewId);
        return reviewId+" review deleted";
    }

}
