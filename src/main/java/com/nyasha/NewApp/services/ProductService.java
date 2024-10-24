package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.Product;
import com.nyasha.NewApp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsSortedBy(String sortBy) {
        switch (sortBy) {
            case "price":
                return productRepository.findByOrderByPriceAsc();
            case "size":
                return productRepository.findByOrderBySizeAsc();
            case "type":
                return productRepository.findByOrderByTypeAsc();
            case "name":
                return productRepository.findByOrderByNameAsc();
            case "popularity":
                return productRepository.findByOrderByPopularityScoreDesc();
            default:
                return productRepository.findAll();
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow();
        // Update fields
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

