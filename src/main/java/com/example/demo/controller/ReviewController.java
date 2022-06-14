package com.example.demo.controller;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.ReviewModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> getHello() {

        return ResponseEntity.status(HttpStatus.OK).body("Hello world");
    }

    //Insert a new review in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertReview(
            @RequestBody ReviewDTO reviewDTO
            ) {
        if(reviewDTO != null){
            boolean inserted = reviewService.insertReview(reviewDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes user by review id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteReview(
            @RequestParam UUID reviewID
    ){
        if(reviewID != null){
            boolean deleted = reviewService.deleteReview(reviewID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all reviews in db
    @GetMapping(value="/list")
    public ResponseEntity<List<ReviewModel>> getReviews(){
        List<ReviewModel> users = reviewService.getReviews();
        if(!users.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
