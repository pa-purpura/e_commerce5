package com.example.demo.model;

import java.sql.Timestamp;
import java.util.UUID;

public class WishlistModel {
    private UUID id;
    private String name;
    private UUID user_id;
    private Timestamp created_at;

    public WishlistModel(UUID id, String name, UUID user_id, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.created_at = created_at;
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

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
