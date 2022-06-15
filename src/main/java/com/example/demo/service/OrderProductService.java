package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.OrderModel;
import com.example.demo.model.OrderProductModel;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderProductService {

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public boolean insertInOrder(OrderModel orderModel, ProductModel productModel, OrderProductModel order_productModel){
        OrderProductModel orderProductModel = new OrderProductModel(orderModel.getId(), productModel.getId() , order_productModel.getQuantity());
        return this.orderProductRepository.insertInOrder(order_productModel);
    }

    public boolean deleteByOrderID(UUID orderID){
        return orderProductRepository.deleteByOrderID(orderID);
    }

    public List<OrderProductModel> getOrderProducts(UUID orderID){
        return orderProductRepository.getOrderProducts(orderID);
    }
}
