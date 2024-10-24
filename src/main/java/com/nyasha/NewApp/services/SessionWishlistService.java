package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class SessionWishlistService {
    private List<Product> sessionWishlist = new ArrayList<>();

    public void addToWishlist(Product product) {
        sessionWishlist.add(product);
    }

    public void removeFromWishlist(Long productId) {
        sessionWishlist.removeIf(product -> product.getId().equals(productId));
    }

    public List<Product> getWishlist() {
        return new ArrayList<>(sessionWishlist);
    }
}
