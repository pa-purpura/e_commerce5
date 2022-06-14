package com.example.demo.controller;

import com.example.demo.dto.SellerDTO;
import com.example.demo.model.SellerModel;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    //Insert a new seller in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertSeller(
            @RequestBody SellerDTO sellerDTO
    ) {
        if(sellerDTO != null){
            boolean inserted = sellerService.insertSeller(sellerDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes seller by user id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteSeller(
            @RequestParam UUID sellerID
    ){
        if(sellerID != null){
            boolean deleted = sellerService.deleteSeller(sellerID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all sellers in db
    @GetMapping(value="/list")
    public ResponseEntity<List<SellerModel>> getSellers(){
        List<SellerModel> sellers = sellerService.getSellers();
        if(!sellers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(sellers);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
