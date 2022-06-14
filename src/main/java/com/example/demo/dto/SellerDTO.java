package com.example.demo.dto;

import java.util.UUID;

public class SellerDTO {
    //Attr

    private String full_name;
    private String address;


    public SellerDTO(String full_name, String address) {
        this.full_name = full_name;
        this.address = address;
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
