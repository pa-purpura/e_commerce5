package com.example.demo.repository;

import com.example.demo.model.CartModel;
import com.example.demo.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertCart(CartModel cartModel){
        int rowsAffected = jdbcTemplate.update(
                "Insert Into cart (id, user_id) VALUES (?,?)",
                cartModel.getId(),
                cartModel.getUser_id());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID cartID){
        int rowsAffected = jdbcTemplate.update(
                "delete from cart where id=?",
                cartID);
        return rowsAffected > 0;
    }

    public boolean deleteByUserID(UUID userID){
        int rowsAffected = jdbcTemplate.update(
                "delete from cart where user_id=?",
                userID);
        return rowsAffected > 0;
    }

    public CartModel getCartByUserID(UUID userID){
        List<CartModel> cartList = jdbcTemplate.query("select * from cart where cart.user_id ='" + userID+ "';",
                (rs, rowNum) ->
                        new CartModel(
                                (UUID) rs.getObject("id"),
                                (UUID) rs.getObject("user_id")
                        )
        );
        return cartList.get(0);
    }

    public List<CartModel> getCarts(){
        return jdbcTemplate.query("select * from cart;",
                (rs, rowNum) ->
                        new CartModel(
                                (UUID) rs.getObject("id"),
                                (UUID) rs.getObject("user_id")
                        )
        );
    }


}
