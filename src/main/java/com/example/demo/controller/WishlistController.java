package com.example.demo.controller;

import com.example.demo.model.ProductViewModel;
import com.example.demo.dto.WishlistDTO;
import com.example.demo.model.WishlistModel;
import com.example.demo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createWishlist(@RequestBody WishlistDTO wishlistDTO){
        if(wishlistService.insertWishlist(wishlistDTO)) return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PostMapping(value="/addToWishlist")
    public ResponseEntity<Void> addToWishlist(@RequestParam UUID wishlistID,@RequestParam UUID productID){
        if(this.wishlistService.addToWishlist(wishlistID,productID))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value= "/getListofWishlist")
    public ResponseEntity<List<WishlistModel>> getWishlistByUser(@RequestParam UUID user_id){
        List<WishlistModel> var = this.wishlistService.getWishlistByUser(user_id);
        if(var.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(var);
    }


    @DeleteMapping (value= "/DeleteWishlistByID")
    public ResponseEntity<Void> deleteWishlistByID(@RequestParam UUID id){
        if(this.wishlistService.deleteWishlistByID(id)) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value="/DeleteAll")
    public ResponseEntity<Void> deleteAllWishlist(@RequestParam UUID userId){
        if(this.wishlistService.deleteAllWishlist(userId)) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    //TODO check se funziona(non prende gli UUID ma le stringhe si)
@DeleteMapping(value= "/deleteProductFromWishlist")
    public ResponseEntity<Void> deleteProdWishlist(@RequestParam UUID wishlist_id, @RequestParam UUID product_id){
        if(this.wishlistService.deleteFromWishlist(wishlist_id,product_id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
@GetMapping(value="/getProductsFromWishlist")
   public ResponseEntity<List<ProductViewModel>> getProductsFromWishlist(@RequestParam UUID wishlist_id){
         List<ProductViewModel> tmp = this.wishlistService.getProductsFromWishlist(wishlist_id);
         if(tmp.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         return ResponseEntity.status(HttpStatus.OK).body(tmp);
   }

   //TODO minchia che odio gli uuid
   @PutMapping(value="/addWishlistToCart")
   public ResponseEntity<Void> insertWishlistToCart(@RequestParam UUID wishlist_id, @RequestParam UUID user_id){
        if(this.wishlistService.addWishlistToCart(wishlist_id,user_id))
            return ResponseEntity.status(HttpStatus.OK).build();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

   }
//TODO stessa cosa di sopra p.s ha senso ?
    @PutMapping(value="/addProductFromWishlistToCart")
    public ResponseEntity<Void> insertProductFromWishlistToCart(
            @RequestParam UUID wishlist_id, UUID user_id,UUID product_id){
        if(this.wishlistService.addProductFromWishlistToCart(wishlist_id,user_id,product_id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

    }

}
