package com.example.demo.model;

import java.util.UUID;

public class ProductModel {
    //Attr
    private UUID id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private UUID seller_id;

    public ProductModel(UUID id, String name, double price, int stock, String description, UUID seller_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.seller_id = seller_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(UUID seller_id) {
        this.seller_id = seller_id;
    }
}
