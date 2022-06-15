package com.example.demo.controller;


import com.example.demo.model.CartModel;
import com.example.demo.model.CartProductModel;
import com.example.demo.service.CartService;
import com.example.demo.service.CartProductService;
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
    private CartProductService cart_productService;

    @Autowired
    public CartController(CartService cartService, CartProductService cart_productService) {
        this.cartService = cartService;
        this.cart_productService = cart_productService;
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


    //------------------CART_PRODUCT

    //Insert a new cart_product in db
    @PostMapping(value = "/createProduct")
    public ResponseEntity<Void> insertCartProduct(
            @RequestBody CartProductModel cart_productModel
    ) {
        if(cart_productModel != null){
            boolean inserted = cart_productService.insertCartProduct(cart_productModel);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Buy
    @PostMapping(value = "/buy")
    public ResponseEntity<Void> cartBuy(
            @RequestParam UUID idCart
    ) {
        if(idCart != null){
            boolean inserted = false;
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes all products in a cart by cart id
    @DeleteMapping(value="/destroyCartProduct")
    public ResponseEntity<Void> deleteCartProduct(
            @RequestParam UUID cartID
    ){
        if(cartID != null){
            boolean deleted = cart_productService.deleteCartProduct(cartID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes product in a cart by cart id and product id
    @DeleteMapping(value="/destroyCartProductByID")
    public ResponseEntity<Void> deleteCartProductByProductId(
            @RequestParam UUID cartID, UUID productID
    ){
        if(cartID != null && productID != null){
            boolean deleted = cart_productService.deleteCartIdProductId(cartID, productID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets products by cart id
    @GetMapping(value="/productsByCartID")
    public ResponseEntity<List<CartProductModel>> getCartProductsByUserID(
            @RequestParam UUID cartID
    ){
        List<CartProductModel> products = cart_productService.getCartProductsByCartID(cartID);
        if(products != null){
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    //Gets products in carts
    @GetMapping(value="/listCartProducts")
    public ResponseEntity<List<CartProductModel>> getCartProducts(
    ){
        List<CartProductModel> products = cart_productService.getCartProducts();
        if(products != null){
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
