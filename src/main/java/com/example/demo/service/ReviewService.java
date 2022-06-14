package com.example.demo.service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.ReviewModel;
import com.example.demo.model.UserModel;
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
    public ReviewService(UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
    }

    public boolean insertReview(ReviewDTO reviewDTO){
        UUID reviewID = UUID.randomUUID();

        ReviewModel reviewToInsert = new ReviewModel(reviewID, reviewDTO.getOpinion(), reviewDTO.getRating(),
                reviewDTO.getUserID(), reviewDTO.getProductID());

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
            return new ReviewDTO(reviewModel.getOpinion(), reviewModel.getRating(), reviewModel.getUserID(),
                    reviewModel.getProductID());
        }
        return null;
    }

}
