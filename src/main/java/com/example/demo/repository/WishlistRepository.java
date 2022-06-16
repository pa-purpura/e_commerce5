package com.example.demo.repository;


import com.example.demo.model.WishlistModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class WishlistRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

public boolean insertWishlist(WishlistModel wish){

    int rowsAffected = jdbcTemplate.update("INSERT INTO wishlist (id,name,user_id,created_at) " +
                    "VALUES (?,?,?,?)",
            wish.getId(),
            wish.getName(),
            wish.getUser_id(),
            wish.getCreated_at());
    return rowsAffected >0;
}

public List<WishlistModel> getWishlistByUser(UUID user_id){
    return jdbcTemplate.query("SELECT * FROM wishlist WHERE wishlist.user_id= '" + user_id +"';",
            (rs,rowNum)->
                    new WishlistModel(
                            (UUID) rs.getObject("id"),
                            rs.getString("name"),
                            (UUID) rs.getObject("user_id"),
                            (Timestamp) rs.getObject("created_at")

                    )
            );

}

/*
public boolean checkForWishlist(UUID wishlist_id){
 List<UUID> tmp =  this.jdbcTemplate.query("SELECT id FROM wishlist WHERE wishlist.id= '" +wishlist_id + "';",
         (rs,rowNum)-> UUID.fromString(rs.getObject("id").toString()));
   return tmp.isEmpty();
}
*/
public boolean deleteWishlistByID(UUID id){
    int rows = this.jdbcTemplate.update("DELETE  FROM wishlist WHERE wishlist.id =?", id);
    return rows>0;
}

public boolean deleteAll(UUID userId){
    int rows = this.jdbcTemplate.update("DELETE  FROM wishlist WHERE wishlist.user_id=?",userId);
    return rows >0;
}
/*
public boolean addToWishlist(UUID wishlist_id,UUID product_id){
    int rows = this.jdbcTemplate.update("INSERT INTO wishlist_product (wishlist_id,produc_id)" +
            " VALUES (?,?)", wishlist_id,product_id);
    return rows>0;
}
*/
}
