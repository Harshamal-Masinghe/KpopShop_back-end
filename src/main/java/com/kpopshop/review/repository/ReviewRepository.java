package com.kpopshop.review.repository;

import com.kpopshop.review.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
