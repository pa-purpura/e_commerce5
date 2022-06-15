package com.example.demo.model;

import java.util.UUID;

public class WishlistProductModel {
    private UUID wishlist_id;
    private UUID product_id;

    public WishlistProductModel(UUID wishlist_id, UUID product_id) {
        this.wishlist_id = wishlist_id;
        this.product_id = product_id;
    }

    public UUID getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(UUID wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }
}
