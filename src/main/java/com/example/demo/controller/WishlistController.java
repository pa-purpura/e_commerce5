package com.example.demo.controller;

import com.example.demo.dto.WishlistDTO;
import com.example.demo.model.WishlistModel;
import com.example.demo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createWishlist(@RequestBody WishlistDTO wishlistDTO){
        if(wishlistService.insertWishlist(wishlistDTO)) return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PutMapping(value="/addToWishlist")
    public ResponseEntity<Void> addToWishlist(@RequestParam UUID wishlist_id,UUID product_id){
        if(this.wishlistService.addToWishlist(wishlist_id,product_id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value= "/getListofWishlist")
    public ResponseEntity<List<WishlistModel>> getWishlistByUser(@RequestParam UUID user_id){
        List<WishlistModel> var = this.wishlistService.getWishlistByUser(user_id);
        if(var.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(var);
    }


    @DeleteMapping (value= "/DeleteWishlistByID")
    public ResponseEntity<Void> deleteWishlistByID(@RequestParam UUID id){
        if(this.wishlistService.deleteWishlistByID(id)) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value="/DeleteAll")
    public ResponseEntity<Void> deleteAllWishlist(@RequestParam UUID userId){
        if(this.wishlistService.deleteAllWishlist(userId)) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
/*
    public ResponseEntity<Void> deleteProdWishlist(UUID wishlist_id, UUID product_id){
        if(this.wishlistService.deleteFromWishlist(wishlist_id,product_id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
*/
}
