package com.example.demo.dto;

import java.util.UUID;


public class ReviewDTO {
    //Attr
    private String opinion;
    private Integer rating;
    private final UUID user_id;
    private final UUID product_id;


    //Cost

    public ReviewDTO(String opinion, Integer rating, UUID user_id, UUID product_id) {
        this.opinion = opinion;
        this.rating = rating;
        this.user_id = user_id;
        this.product_id = product_id;
    }


    //Metodi


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
