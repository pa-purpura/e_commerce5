package com.example.demo.model;

import java.util.UUID;

public class SellerModel {
    //Attr
    private UUID id;
    private String full_name;
    private String address;


    public SellerModel(UUID id, String full_name, String address) {
        this.id = id;
        this.full_name = full_name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
