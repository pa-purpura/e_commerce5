package com.example.demo.service;

import com.example.demo.model.CartModel;
import com.example.demo.model.CartProductModel;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private CartProductService cartProductService;
    @Autowired
    public CartService(CartRepository cartRepository, CartProductService cartProductService) {
        this.cartRepository = cartRepository;
        this.cartProductService = cartProductService;
    }


    public boolean insertCart(UUID userID){
        UUID idCart = UUID.randomUUID();
        CartModel cartModel = new CartModel(idCart, userID);
        return this.cartRepository.insertCart(cartModel);
    }

    public boolean deleteCart(UUID cartID){

        return cartRepository.deleteByID(cartID);
    }

    public boolean deleteCartByUserID(UUID userID){

        return cartRepository.deleteByUserID(userID);
    }

    public List<CartModel> getCarts(){

        return cartRepository.getCarts();
    }

    public CartModel getCartByUserID (UUID userID){

        return cartRepository.getCartByUserID(userID);
    }

    public UUID cartBuy (UUID idCart){
        List<CartProductModel> cartProducts = cartProductService.getCartProductsByCartID(idCart);
        if(cartProducts!= null){


        }


        return UUID.randomUUID();
    }

}
