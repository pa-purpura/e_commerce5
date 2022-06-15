package com.example.demo.service;

import com.example.demo.model.CartProductModel;
import com.example.demo.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartProductService {
    private CartProductRepository cart_productRepository;

    @Autowired
    public CartProductService(CartProductRepository cart_productRepository) {
        this.cart_productRepository = cart_productRepository;
    }

    public boolean insertCartProduct(CartProductModel cart_productModel){
        return this.cart_productRepository.insertCartProduct(cart_productModel);
    }

    public boolean deleteCartProduct(UUID cartID){
        return cart_productRepository.deleteByID(cartID);
    }

    public boolean deleteCartIdProductId(UUID cartID, UUID product_id){
        return cart_productRepository.deleteByCartIDProductID(cartID, product_id);
    }

    public List<CartProductModel> getCartProductsByCartID(UUID cartID){
        return cart_productRepository.getCartProductsByCartID(cartID);
    }

    public List<CartProductModel> getCartProducts(){
        return cart_productRepository.getCartProducts();
    }
}
