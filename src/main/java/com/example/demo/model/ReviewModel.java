package com.example.demo.model;

import java.util.UUID;

public class ReviewModel {
    //Attr

    private final UUID id;
    private String opinion;
    private Integer rating;
    private final UUID userID;
    private final UUID productID;

    //Cost

    public ReviewModel(UUID id, String opinion, Integer rating, UUID userID, UUID productID) {
        this.id = id;
        this.opinion = opinion;
        this.rating = rating;
        this.userID = userID;
        this.productID = productID;
    }


    //Metodi

    public UUID getId() {
        return id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getProductID() {
        return productID;
    }

}
