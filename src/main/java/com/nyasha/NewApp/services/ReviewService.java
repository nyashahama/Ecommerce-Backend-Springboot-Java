package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.Review;
import com.nyasha.NewApp.repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getLatestReviewsForProduct(Long productId) {
        return reviewRepository.findTop3ByProductIdOrderByCreatedAtDesc(productId);
    }

    public List<Review> getAllReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElseThrow();
        // Update fields
        return reviewRepository.save(existingReview);
    }
}
