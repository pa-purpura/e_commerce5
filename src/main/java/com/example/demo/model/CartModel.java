package com.example.demo.model;

import java.util.UUID;

public class CartModel {
    private UUID id;
    private UUID user_id;

    private double total;

    public CartModel() {
    }

    public CartModel(UUID id, UUID user_id) {
        this.id = id;
        this.user_id = user_id;
    }

    public CartModel(UUID id, UUID user_id, double total) {
        this.id = id;
        this.user_id = user_id;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
