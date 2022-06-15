package com.example.demo.repository;

import com.example.demo.model.OrderProductModel;
import com.example.demo.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    // verificare una volta mergiato con Order branch
    public boolean insertInOrder(OrderModel orderModel, ProductModel productModel, OrderProductModel order_productModel){
        int rowsAffected = jdbcTemplate.update(
                //verificare il nome dei campi in order model
                "Insert Into order_product (order_id, product_id, quantity) VALUES (?,?,?)",
                //verificare il nome id dentro orderModel
                orderModel.getId(),
                productModel.getId(),
                order_productModel.getQuantity());
        return rowsAffected > 0;
    }

    public boolean deleteByOrderID(UUID orderID){
        int rowsAffected = jdbcTemplate.update(
                "delete from order_product where order_id=?",
                orderID);
        return rowsAffected > 0;
    }

    public List<OrderProductModel> getOrderProducts(orderID){

        return jdbcTemplate.query("select op.*, p.name as name, p.price as price from order_product op, product p, "
                "where product.seller_id ='" + orderID + "';",
                (rs, rowNum) ->
                        new OrderProductModel(
                                (UUID) rs.getObject("order_id"),
                                (UUID) rs.getObject("product_id"),
                                rs.getInt("quantity"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
        );
    }

}
