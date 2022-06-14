package com.example.demo.service;

import com.example.demo.dto.SellerDTO;
import com.example.demo.model.SellerModel;
import com.example.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SellerService {
    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public boolean insertSeller(SellerDTO sellerDTO){
        UUID idSeller = UUID.randomUUID();
        SellerModel sellerModel = new SellerModel(idSeller, sellerDTO.getFull_name(), sellerDTO.getAddress());
        return this.sellerRepository.insertSeller(sellerModel);
    }

    public boolean deleteSeller(UUID sellerID){
        return sellerRepository.deleteSeller(sellerID);
    }

    public List<SellerModel> getSellers(){
        return sellerRepository.getSellers();
    }

}
