package com.example.demo.service;

import com.example.demo.dto.WishlistDTO;
import com.example.demo.model.*;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.CartRepository;
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
    private CartProductRepository cartProductRepository;
    private CartRepository cartRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository,
                           WishlistProductRepository wishlistProductRepository,
                           CartProductRepository cartProductRepository,
                           CartRepository cartRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistProductRepository = wishlistProductRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
    }


    public boolean insertWishlist(WishlistDTO wishlistDTO) {
        if (wishlistDTO.getName().length() == 0 || wishlistDTO.getUser_id() == null) return false;
        UUID tmp = UUID.randomUUID();
        Timestamp time = Timestamp.from(Instant.now());
        return wishlistRepository.insertWishlist(new WishlistModel(tmp, wishlistDTO.getName(),
                wishlistDTO.getUser_id(), time));
    }

    public List<WishlistModel> getWishlistByUser(UUID user_id) {
        return this.wishlistRepository.getWishlistByUser(user_id);
    }


    public boolean deleteWishlistByID(UUID id) {
        // if(this.wishlistRepository.checkForWishlist(id))
        return this.wishlistRepository.deleteWishlistByID(id);
        // return false;
    }

    public boolean deleteAllWishlist(UUID userId) {
        //check per vedere se l'utente esiste
        return this.wishlistRepository.deleteAll(userId);
    }

    public boolean addToWishlist(UUID wishlistID, UUID productID) {
        if (wishlistID == null || productID == null) return false;
        return this.wishlistProductRepository.insertProductToWishlist(
                new WishlistProductModel(wishlistID, productID));
    }

    public boolean deleteFromWishlist(UUID wishlist_id, UUID product_id) {
        if (wishlist_id == null || product_id == null) return false;
        return this.wishlistProductRepository.deleteFromWishlist(wishlist_id, product_id);
    }


    public boolean addWishlistToCart(UUID wishlistID,UUID userID) {

        CartModel crt = this.cartRepository.getCartByUserID(userID);
        if (crt != null) {
            List<ProductViewModel> tmp = this.wishlistProductRepository.getProductsFromWishlist(wishlistID);
            for (ProductViewModel p : tmp) {
                boolean b = this.cartProductRepository.insertCartProduct(
                        new CartProductModel(
                                crt.getId(),
                                p.getProduct_id(),
                                1));
                if (!b) return false;
            }
            return true;
        }
        return false;
    }

    public boolean addProductFromWishlistToCart(UUID wishlistID,UUID userID,UUID product_id) {

        CartModel crt = this.cartRepository.getCartByUserID(userID);
        if (crt != null) {
            List<ProductViewModel> tmp = this.wishlistProductRepository.getProductsFromWishlist(wishlistID);
            for (ProductViewModel p : tmp) {
                if(p.getProduct_id().equals(product_id)) {
                    return  this.cartProductRepository.insertCartProduct(
                            new CartProductModel(
                                    crt.getId(),
                                    p.getProduct_id(),
                                    1));

                }
            }
            return false;
        }
        return false;
    }



    public List<ProductViewModel> getProductsFromWishlist(UUID wishlist_id) {
       if(wishlist_id == null) return null;
       return this.wishlistProductRepository.getProductsFromWishlist(wishlist_id);
    }
}

