package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.model.ProductModel;
import com.example.demo.model.SellerModel;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean insertProduct(ProductDTO productDTO){
        UUID idProduct = UUID.randomUUID();
        ProductModel productModel = new ProductModel(idProduct, productDTO.getName(), productDTO.getPrice(),
                productDTO.getStock(), productDTO.getDescription(), productDTO.getSeller_id());
        return this.productRepository.insertProduct(productModel);
    }

    public boolean deleteProduct(UUID productID){
        return productRepository.deleteByID(productID);
    }

    public List<ProductModel> getProducts(){
        return productRepository.getProducts();
    }

    public List<ProductModel> getProductsBySellerID(UUID sellerID){
        return productRepository.getProductsBySellerID(sellerID);
    }
}
