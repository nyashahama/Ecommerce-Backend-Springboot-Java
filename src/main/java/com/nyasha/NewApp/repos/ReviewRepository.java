package com.nyasha.NewApp.repos;

import com.nyasha.NewApp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop3ByProductIdOrderByCreatedAtDesc(Long productId);
    List<Review> findByProductId(Long productId);
    List<Review> findByUserIdAndProductId(Long userId, Long productId);
}
