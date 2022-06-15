package com.example.demo.repository;


import com.example.demo.model.CartProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CartProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertCartProduct(CartProductModel cart_productModel){
        int rowsAffected = jdbcTemplate.update(
                "Insert Into cart_product (cart_id, product_id, quantity) VALUES (?,?,?)",
                cart_productModel.getCart_id(),
                cart_productModel.getProduct_id(),
                cart_productModel.getQuantity());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID cartID){
        int rowsAffected = jdbcTemplate.update(
                "delete from cart_product where cart_id=?",
                cartID);
        return rowsAffected > 0;
    }

    public boolean deleteByCartIDProductID(UUID cartID, UUID productID){
        int rowsAffected = jdbcTemplate.update(
                "delete from cart_product where cart_id='" + cartID+"' and product_id='" + productID+"';");
        return rowsAffected > 0;
    }

    public List<CartProductModel> getCartProductsByCartID(UUID cartID){
        return jdbcTemplate.query("Select\n" +
                        "    cp.*,\n" +
                        "    p.name as product_name,\n" +
                        "    u.name as user_name\n" +
                        "    from cart_product cp, product p, cart c, \"user\" u\n" +
                        "where cp.cart_id = '"+ cartID + "' and cp.product_id = p.id and cp.cart_id = c.id and c.user_id = u.id",
                (rs, rowNum) ->
                        new CartProductModel(
                                (UUID) rs.getObject("cart_id"),
                                (UUID) rs.getObject("product_id"),
                                rs.getInt("quantity"),
                                rs.getString("product_name"),
                                rs.getString("user_name")
                        )
        );
    }

    public List<CartProductModel> getCartProducts(){
        return jdbcTemplate.query("Select\n" +
                        "    cp.*,\n" +
                        "    p.name as product_name,\n" +
                        "    u.name as user_name\n" +
                        "    from cart_product cp, product p, cart c, \"user\" u\n" +
                        "where cp.cart_id = c.id and cp.product_id = p.id and cp.cart_id = c.id and c.user_id = u.id;",
                (rs, rowNum) ->
                        new CartProductModel(
                                (UUID) rs.getObject("cart_id"),
                                (UUID) rs.getObject("product_id"),
                                rs.getInt("quantity"),
                                rs.getString("product_name"),
                                rs.getString("user_name")
                        )
        );
    }


}
