package com.example.demo.controller;


import com.example.demo.model.CartModel;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Insert a new cart in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertCart(
            @RequestParam UUID userID
    ) {
        if(userID != null){
            boolean inserted = cartService.insertCart(userID);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes cart by cart id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteCart(
            @RequestParam UUID cartID
    ){
        if(cartID != null){
            boolean deleted = cartService.deleteCart(cartID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes cart by user id
    @DeleteMapping(value="/destroyByUserId")
    public ResponseEntity<Void> deleteCartByUserID(
            @RequestParam UUID userID
    ){
        if(userID != null){
            boolean deleted = cartService.deleteCartByUserID(userID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all carts in db
    @GetMapping(value="/list")
    public ResponseEntity<List<CartModel>> getCarts(){
        List<CartModel> carts = cartService.getCarts();
        if(!carts.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(carts);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    //Gets cart by user id in db
    @GetMapping(value="/listByUser")
    public ResponseEntity<CartModel> getCartByUserID(
            @RequestParam UUID userID
    ){
        CartModel cart = cartService.getCartByUserID(userID);
        if(cart != null){
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
