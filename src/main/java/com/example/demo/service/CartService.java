package com.example.demo.service;

import com.example.demo.enums.OrderStatus;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private CartProductService cartProductService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;

    private ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartProductService cartProductService, UserRepository userRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartProductService = cartProductService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
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

    public CartModel getCartByCartId (UUID cartID){
        return cartRepository.getCartByCartID(cartID);
    }

    public UUID cartBuy (UUID idCart){
        CartModel cart = getCartByCartId(idCart);
        if(cart != null){
            UserModel user = userRepository.getUserByID(cart.getUser_id());
            if(user!=null){
                if(user.getBalance()>= cart.getTotal()){
                    //Crea ordine
                    UUID orderId = UUID.randomUUID();
                    OrderModel orderModel = new OrderModel(orderId, user.getId(), OrderStatus.processing, cart.getTotal(), Timestamp.from(Instant.now()));
                    orderRepository.insertOrder(orderModel);
                    //Inserire prodotti dell'ordine
                    List<CartProductModel> cartProductList = cartProductService.getCartProductsByCartID(idCart);
                    for(CartProductModel cartProduct : cartProductList){
                        //Adds products to order_product
                        OrderProductModel orderProductModel = new OrderProductModel(orderId, cartProduct.getProduct_id(), cartProduct.getQuantity());
                        orderProductRepository.insertInOrder(orderProductModel);

                        //Updates stock product
                        productRepository.updateStockByProductId(cartProduct.getProduct_id(), cartProduct.getQuantity());

                    }
                    //Updates user's balance
                    userRepository.updateBalanceByUserId(user.getId(), cart.getTotal());
                    return orderId;
                }
            }
        }
        return null;
    }

}
