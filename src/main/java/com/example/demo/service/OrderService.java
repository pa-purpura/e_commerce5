package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.enums.OrderStatus;
import com.example.demo.model.OrderModel;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService( OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean insertOrder(OrderDTO orderDTO){
        UUID idOrder = UUID.randomUUID();
        OrderModel orderModel = new OrderModel(idOrder,
                orderDTO.getUser_id(),
                orderDTO.getOrderStatus(),
                orderDTO.getTotal(),
                Timestamp.from(Instant.now())
        );
        return this.orderRepository.insertOrder(orderModel);
    }

    public boolean deleteOrder(UUID orderID){
        return orderRepository.deleteByID(orderID);
    }

    public List<OrderModel> getOrders(){
        return orderRepository.getOrders();
    }

    public List<OrderModel> getOrdersByUserID(UUID userID){
        return orderRepository.getOrdersByUserID(userID);
    }

    public boolean updateOrderStatusByOrderId(UUID orderID, OrderStatus orderStatus) {
        return orderRepository.updateOrderStatusByOrderId(orderID,orderStatus);
    }

}
