package com.nyasha.NewApp.services;

import com.nyasha.NewApp.model.WishlistItem;
import com.nyasha.NewApp.repos.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    public List<WishlistItem> getWishlistItems(Long userId) {
        return wishlistItemRepository.findByUserId(userId);
    }

    public WishlistItem addToWishlist(WishlistItem wishlistItem) {
        return wishlistItemRepository.save(wishlistItem);
    }

    public void removeFromWishlist(Long wishlistItemId) {
        wishlistItemRepository.deleteById(wishlistItemId);
    }
}
