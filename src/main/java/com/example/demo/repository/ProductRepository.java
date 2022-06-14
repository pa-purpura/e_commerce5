package com.example.demo.repository;

import com.example.demo.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertProduct(ProductModel productModel){
        int rowsAffected = jdbcTemplate.update(
                "Insert Into product (id, name, price, stock, description, seller_id) VALUES (?,?,?,?,?,?)",
                productModel.getId(),
                productModel.getName(),
                productModel.getPrice(),
                productModel.getStock(),
                productModel.getDescription(),
                productModel.getSeller_id());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID productID){
        int rowsAffected = jdbcTemplate.update(
                "delete from product where id=?",
                productID);
        return rowsAffected > 0;

    }

    public List<ProductModel> getProducts(){

        return jdbcTemplate.query("select * from product;",
                (rs, rowNum) ->
                        new ProductModel(
                                (UUID) rs.getObject("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getString("description"),
                                (UUID) rs.getObject("seller_id")
                        )
        );
    }

    public List<ProductModel> getProductsBySellerID(UUID sellerID){
        return jdbcTemplate.query("Select * \n" +
                        "from product\n" +
                        "where product.seller_id ='" + sellerID + "';",
                (rs, rowNum) ->
                        new ProductModel(
                                (UUID) rs.getObject("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getString("description"),
                                (UUID) rs.getObject("seller_id")
                        )
        );
    }
}
