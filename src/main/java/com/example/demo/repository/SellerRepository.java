package com.example.demo.repository;

import com.example.demo.model.SellerModel;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SellerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertSeller(SellerModel sellerModel){
        int rowsAffected = jdbcTemplate.update(
                "Insert Into seller (id, full_name, address) VALUES (?,?,?)",
                sellerModel.getId(),
                sellerModel.getFull_name(),
                sellerModel.getAddress());
        return rowsAffected > 0;
    }

    public boolean deleteSeller(UUID id){
        int rowsAffected = jdbcTemplate.update(
                "delete from seller where id=?",
                id);
        return rowsAffected > 0;
    }

    public List<SellerModel> getSellers(){

        return jdbcTemplate.query("select * from seller;",
                (rs, rowNum) ->
                        new SellerModel(
                                (UUID) rs.getObject("id"),
                                rs.getString("full_name"),
                                rs.getString("address")
                        )
        );
    }

}
