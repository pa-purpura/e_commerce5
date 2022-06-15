package com.example.demo.model;

import java.sql.Timestamp;
import java.util.UUID;
import com.example.demo.enums.OrderStatus;

public class OrderModel {
    //Attr
    private final UUID id;
    private final UUID user_id;
    private OrderStatus orderStatus;
    private Double total;
    /*timestamp from json version
    private LocalDateTime created_at;
    */
    Timestamp created_at;

    //Const

    public OrderModel(UUID id, UUID user_id, OrderStatus orderStatus, Double total, Timestamp created_at) {
        this.id = id;
        this.user_id = user_id;
        this.orderStatus = orderStatus;
        this.total = total;
        this.created_at = created_at;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
