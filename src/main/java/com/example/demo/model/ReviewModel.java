package com.example.demo.model;

import java.util.UUID;

public class ReviewModel {
    //Attr

    private final UUID id;
    private String opinion;
    private Integer rating;
    private final UUID user_id;
    private final UUID product_id;

    //Cost

    public ReviewModel(UUID id, String opinion, Integer rating, UUID user_id, UUID product_id) {
        this.id = id;
        this.opinion = opinion;
        this.rating = rating;
        this.user_id = user_id;
        this.product_id = product_id;
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

    public UUID getUser_id() {
        return user_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

}
