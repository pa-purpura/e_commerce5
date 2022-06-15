package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import java.util.UUID;


public class OrderDTO {
    //Attr
    private final UUID user_id;

    private OrderStatus orderStatus;
    private Double total;

    /* Timestamp provided from Json Version
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime created_at;
    */
    //Cost

    public OrderDTO(UUID user_id, OrderStatus orderStatus, Double total) {
        this.user_id = user_id;
        this.orderStatus = orderStatus;
        this.total = total;
    }


    //Metodi


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

}
