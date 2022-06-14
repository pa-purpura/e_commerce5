package com.example.demo.service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.model.ReviewModel;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public boolean insertReview(ReviewDTO reviewDTO){
        UUID reviewID = UUID.randomUUID();

        ReviewModel reviewToInsert = new ReviewModel(reviewID, reviewDTO.getOpinion(), reviewDTO.getRating(),
                reviewDTO.getUser_id(), reviewDTO.getProduct_id());

        return reviewRepository.insertReview(reviewToInsert);
    }

    public boolean deleteReview(UUID reviewID){
        return reviewRepository.deleteByID(reviewID);
    }

    public List<ReviewDTO> getReviewsNoID(){
        List<ReviewModel> listReviews = reviewRepository.getReviews();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for(ReviewModel reviewModel : listReviews){
            ReviewDTO reviewDTO = this.fromModelToDto(reviewModel);
            if(reviewDTO!= null){
                reviewDTOS.add(reviewDTO);
            }
        }
        return reviewDTOS;
    }
    public List<ReviewModel> getReviews(){
        return reviewRepository.getReviews();
    }

    private ReviewDTO fromModelToDto(ReviewModel reviewModel){
        if(reviewModel!= null){
            return new ReviewDTO(reviewModel.getOpinion(), reviewModel.getRating(), reviewModel.getUser_id(),
                    reviewModel.getProduct_id());
        }
        return null;
    }

}
