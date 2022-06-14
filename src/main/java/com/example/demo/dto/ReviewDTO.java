package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;


public class ReviewDTO {
    //Attr
    private String opinion;
    private Integer rating;
    private final UUID userID;
    private final UUID productID;


    //Cost

    public ReviewDTO(String opinion, Integer rating, UUID userID, UUID productID) {
        this.opinion = opinion;
        this.rating = rating;
        this.userID = userID;
        this.productID = productID;
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

    public UUID getUserID() {
        return userID;
    }

    public UUID getProductID() {
        return productID;
    }
}
