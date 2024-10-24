package com.nyasha.NewApp.controller;

import com.nyasha.NewApp.model.WishlistItem;
import com.nyasha.NewApp.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistItem>> getWishlistItems(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistService.getWishlistItems(userId));
    }

    @PostMapping
    public ResponseEntity<WishlistItem> addToWishlist(@RequestBody WishlistItem wishlistItem) {
        return ResponseEntity.ok(wishlistService.addToWishlist(wishlistItem));
    }

    @DeleteMapping("/{wishlistItemId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long wishlistItemId) {
        wishlistService.removeFromWishlist(wishlistItemId);
        return ResponseEntity.noContent().build();
    }
}
