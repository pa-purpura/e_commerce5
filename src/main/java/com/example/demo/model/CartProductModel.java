package com.example.demo.model;

import java.util.UUID;

public class CartProductModel {
    private UUID cart_id;
    private UUID product_id;

    private int quantity;

    private String product_name;
    private String user_name;

    public CartProductModel() {
    }

    public CartProductModel(UUID cart_id, UUID product_id, int quantity) {
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public CartProductModel(UUID cart_id, UUID product_id, int quantity, String product_name, String user_name) {
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.product_name = product_name;
        this.user_name = user_name;
    }

    public UUID getCart_id() {
        return cart_id;
    }

    public void setCart_id(UUID cart_id) {
        this.cart_id = cart_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
