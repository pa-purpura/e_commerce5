package com.example.demo.service;

import com.example.demo.model.CartProductModel;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartProductService {
    private CartProductRepository cart_productRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartProductService(CartProductRepository cart_productRepository,
                              ProductRepository productRepository) {
        this.cart_productRepository = cart_productRepository;
        this.productRepository = productRepository;
    }


    public boolean insertCartProduct(CartProductModel cart_productModel){
        ProductModel productModel = productRepository.getProductByID(cart_productModel.getProduct_id());
        if(productModel!= null){
            if(productModel.getStock() >= cart_productModel.getQuantity()){
                return this.cart_productRepository.insertCartProduct(cart_productModel);
            }else{
                cart_productModel.setQuantity(productModel.getStock());
            } //TODO: chiedere melvin come settare un commento del tipo "abbiamo cambiato quantita xoxo"
        }

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
