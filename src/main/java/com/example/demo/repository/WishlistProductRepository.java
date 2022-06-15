package com.example.demo.repository;

import com.example.demo.model.WishlistProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WishlistProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // TODO
 public  boolean insertProductToWishlist(WishlistProductModel wishlistProductModel){
     int rows = jdbcTemplate.update("INSERT INTO wishlist_product (wishlist_id,product_id) " +
             "VALUES (?,?)",
             wishlistProductModel.getWishlist_id(),
             wishlistProductModel.getProduct_id());
     return rows >0;

 }
/*
 public boolean deleteFromWishlist(UUID wishlit_id, UUID product_id){
      //jdbcTemplate.execute("DELETE FROM wishlist_product " +
       //      "WHERE wishlist_product.whislist_id= 'wishlist_id' AND wishlist_product.product_id  = 'product_id' ");

 }
*/


}
