package com.nyasha.NewApp.controller;

import com.nyasha.NewApp.model.Product;
import com.nyasha.NewApp.services.SessionWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/session-wishlist")
public class SessionWishlistController {
    @Autowired
    private SessionWishlistService sessionWishlistService;

    @PostMapping
    public ResponseEntity<Void> addToWishlist(@RequestBody Product product) {
        sessionWishlistService.addToWishlist(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long productId) {
        sessionWishlistService.removeFromWishlist(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getWishlist() {
        return ResponseEntity.ok(sessionWishlistService.getWishlist());
    }
}