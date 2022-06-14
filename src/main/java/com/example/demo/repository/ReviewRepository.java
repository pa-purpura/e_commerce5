package com.example.demo.repository;

import com.example.demo.model.ReviewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ReviewRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertReview(ReviewModel reviewModel){
        int rowsAffected = jdbcTemplate.update(
                "insert into review(id, opinion, rating, user_id, product_id) VALUES (?,?,?,?,?)",
                reviewModel.getId(),
                reviewModel.getOpinion(),
                reviewModel.getRating(),
                reviewModel.getUserID(),
                reviewModel.getProductID());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID reviewID){
        int rowsAffected = jdbcTemplate.update(
                "delete from review where id=?",
                reviewID);
        return rowsAffected > 0;

    }

    public List<ReviewModel> getReviews(){

        return jdbcTemplate.query("select * from review;",
                (rs, rowNum) ->
                        new ReviewModel(
                                (UUID) rs.getObject("id"),
                                rs.getString("opinion"),
                                rs.getInt("rating"),
                                (UUID) rs.getObject("user_id"),
                                (UUID) rs.getObject("product_id")
                        )
        );
    }

}
