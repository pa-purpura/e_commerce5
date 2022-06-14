package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.ProductModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Insert a new product in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertProduct(
            @RequestBody ProductDTO productDTO
    ) {
        if(productDTO != null){
            boolean inserted = productService.insertProduct(productDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes user by product id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteUser(
            @RequestParam UUID productID
    ){
        if(productID != null){
            boolean deleted = productService.deleteProduct(productID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all products in db
    @GetMapping(value="/list")
    public ResponseEntity<List<ProductModel>> getUsers(){
        List<ProductModel> products = productService.getProducts();
        if(!products.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    //Gets all products by Seller id in db
    @GetMapping(value="/listBySeller")
    public ResponseEntity<List<ProductModel>> getUsers(
            @RequestParam UUID sellerID
    ){
        List<ProductModel> products = productService.getProductsBySellerID(sellerID);
        if(!products.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
