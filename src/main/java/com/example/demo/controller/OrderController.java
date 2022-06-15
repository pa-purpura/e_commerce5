package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.OrderModel;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Insert a new order in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertOrder(
            @RequestBody OrderDTO orderDTO
    ) {
        if(orderDTO != null){
            boolean inserted = orderService.insertOrder(orderDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes order by order id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteOrder(
            @RequestParam UUID orderID
    ){
        if(orderID != null){
            boolean deleted = orderService.deleteOrder(orderID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all orders in db
    @GetMapping(value="/list")
    public ResponseEntity<List<OrderModel>> getOrders(){
        List<OrderModel> orders = orderService.getOrders();
        if(!orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    //Gets all orders by user id in db
    @GetMapping(value="/listByUser")
    public ResponseEntity<List<OrderModel>> getOrdersByUser(
            @RequestParam UUID userID
    ){
        List<OrderModel> orders = orderService.getOrdersByUserID(userID);
        if(!orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


}
