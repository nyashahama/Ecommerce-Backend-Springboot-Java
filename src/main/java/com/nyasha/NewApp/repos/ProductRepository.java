package com.nyasha.NewApp.repos;

import com.nyasha.NewApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderBySizeAsc();
    List<Product> findByOrderByTypeAsc();
    List<Product> findByOrderByNameAsc();
    List<Product> findByOrderByPopularityScoreDesc();
}
