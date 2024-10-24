package com.nyasha.NewApp.controller;

import com.nyasha.NewApp.model.Review;
import com.nyasha.NewApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId, @RequestParam(required = false) Boolean all) {
        if (Boolean.TRUE.equals(all)) {
            return ResponseEntity.ok(reviewService.getAllReviewsForProduct(productId));
        }
        return ResponseEntity.ok(reviewService.getLatestReviewsForProduct(productId));
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updateReview(id, review));
    }
}
