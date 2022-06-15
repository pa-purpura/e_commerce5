package com.example.demo.service;

import com.example.demo.dto.WishlistDTO;
import com.example.demo.model.WishlistModel;
import com.example.demo.model.WishlistProductModel;
import com.example.demo.repository.WishlistProductRepository;
import com.example.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;
    private WishlistProductRepository wishlistProductRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, WishlistProductRepository wishlistProductRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistProductRepository = wishlistProductRepository;
    }

    public boolean insertWishlist(WishlistDTO wishlistDTO){
        if(wishlistDTO.getName().length()==0 || wishlistDTO.getUser_id() == null) return false;
        UUID tmp = UUID.randomUUID();
        Timestamp time = Timestamp.from(Instant.now());
        return wishlistRepository.insertWishlist(new WishlistModel(tmp,wishlistDTO.getName(),
                wishlistDTO.getUser_id(),time));
    }

    public List<WishlistModel> getWishlistByUser(UUID user_id){
        return this.wishlistRepository.getWishlistByUser(user_id);
    }

    public boolean deleteWishlistByID(UUID id){
       // if(this.wishlistRepository.checkForWishlist(id))
            return this.wishlistRepository.deleteWishlistByID(id);
       // return false;
    }

    public boolean deleteAllWishlist(UUID userId){
        //check per vedere se l'utente esiste
        return this.wishlistRepository.deleteAll(userId);
    }
public boolean addToWishlist(UUID wishlist_id,UUID product_id){
        if(wishlist_id == null || product_id == null) return false;
        return this.wishlistProductRepository.insertProductToWishlist(
                new WishlistProductModel(wishlist_id,product_id));
}
/*
public boolean deleteFromWishlist(UUID wishlist_id,UUID product_id){
    if(wishlist_id == null || product_id == null) return false;
    return this.wishlistProductRepository.deleteFromWishlist(wishlist_id,product_id);
}
*/
}
