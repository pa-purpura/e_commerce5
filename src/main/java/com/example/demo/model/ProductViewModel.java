package com.example.demo.model;

import java.util.UUID;

// mi serve per vedere gli elementi della wishlist senza la quantità
// (assumendo che in una wishlist io non possa inserire un quantità per un prodotto diversa da 1)
public class ProductViewModel {
    private String name;
    private UUID product_id;
    private double price;
    private String description;
    private UUID seller_id;

    public ProductViewModel(String name, UUID product_id, double price, String description, UUID seller_id) {
        this.name = name;
        this.product_id = product_id;
        this.price = price;
        this.description = description;
        this.seller_id = seller_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
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
