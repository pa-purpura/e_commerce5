package com.example.demo.dto;

import java.util.UUID;

public class WishlistDTO {
    private String name;
    private UUID user_id;

    public WishlistDTO(String name, UUID user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
}
