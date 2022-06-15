package com.example.demo.repository;

import com.example.demo.enums.OrderStatus;
import com.example.demo.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertOrder(OrderModel orderModel){
        int rowsAffected = jdbcTemplate.update(
                "insert into \"order\"(id, user_id, status, total, created_at) VALUES (?,?,?::order_status,?,?)",
                orderModel.getId(),
                orderModel.getUser_id(),
                orderModel.getOrderStatus().name(),
                orderModel.getTotal(),
                orderModel.getCreated_at());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID orderID){
        int rowsAffected = jdbcTemplate.update(
                "delete from \"order\" where id=?",
                orderID);
        return rowsAffected > 0;

    }

    public List<OrderModel> getOrders(){

        return jdbcTemplate.query("select * from \"order\";",
                (rs, rowNum) ->
                        new OrderModel(
                                (UUID) rs.getObject("id"),
                                (UUID) rs.getObject("user_id"),
                                OrderStatus.valueOf(rs.getString("status")),
                                rs.getDouble("total"),
                                rs.getTimestamp("created_at")       //.toLocalDateTime() ts from json version
                        )
        );
    }

    public List<OrderModel> getOrdersByUserID(UUID userID){
        return jdbcTemplate.query("Select * \n" +
                        "from order\n" +
                        "where order.user_id ='" + userID + "';",
                (rs, rowNum) ->
                        new OrderModel(
                                (UUID) rs.getObject("id"),
                                (UUID) rs.getObject("user_id"),
                                OrderStatus.valueOf(rs.getString("status")),
                                rs.getDouble("total"),
                                rs.getTimestamp("created_at")       //.toLocalDateTime() ts from json version
                        )
        );
    }
}
