package com.example.demo.repository;


import com.example.demo.model.ProductViewModel;
import com.example.demo.model.WishlistProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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

 public boolean deleteFromWishlist(UUID wishlist_id, UUID product_id){
     int rows = jdbcTemplate.update("DELETE FROM wishlist_product " +
             "WHERE wishlist_product.wishlist_id= '"
             + wishlist_id +"' AND wishlist_product.product_id= '" +product_id +"';");

     return rows >0;
 }


public boolean checkIfPresent(UUID productID, UUID wishlistID){

    List<WishlistProductModel> tmp = jdbcTemplate.query("SELECT * FROM wishlist_product;",

            (rs,numRow)->  new WishlistProductModel(
                    (UUID) rs.getObject("wishlist_id"),
                    (UUID) rs.getObject("product_id")
            )
        );
    for(WishlistProductModel p: tmp){
        if(p.getWishlist_id().equals(wishlistID) &&
                p.getProduct_id().equals(productID)) return true;
    }
    return false;

}
public List<ProductViewModel> getProductsFromWishlist(UUID wishlist_id){
    return this.jdbcTemplate.query("SELECT product.id, product.name, product.description," +
             " product.price, product.seller_id FROM wishlist_product, product " +
             "WHERE wishlist_product.wishlist_id = '" + wishlist_id +"'" +
                    " AND wishlist_product.product_id = product.id ",
            (rs,numRows)-> new ProductViewModel(
                    rs.getString("name"),
                    (UUID)rs.getObject("id"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    (UUID)rs.getObject("seller_id"))
    );
}
}


