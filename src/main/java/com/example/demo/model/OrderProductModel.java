package com.example.demo.model;

import java.util.UUID;

public class OrderProductModel {

    private UUID order_id;
    private UUID product_id;
    private int quantity;

    private String name;

    private double price;

    public OrderProductModel(UUID order_id, UUID product_id, int quantity, String name, double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

   public OrderProductModel(UUID order_id, UUID product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public UUID getOrder_id() {
        return order_id;
    }

    public void setOrder_id(UUID order_id) {
        this.order_id = order_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
