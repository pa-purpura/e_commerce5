package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> getHello() {

        return ResponseEntity.status(HttpStatus.OK).body("Hello world");
    }

    //Insert a new user in db
    @PostMapping(value = "/create")
    public ResponseEntity<Void> insertUser(
            @RequestBody UserDTO userDTO
            ) {
        if(userDTO != null){
            boolean inserted = userService.insertUser(userDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Deletes user by user id
    @DeleteMapping(value="/destroy")
    public ResponseEntity<Void> deleteUser(
            @RequestParam UUID userID
    ){
        if(userID != null){
            boolean deleted = userService.deleteUser(userID);
            if(deleted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Gets all users in db
    @GetMapping(value="/list")
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> users = userService.getUsers();
        if(!users.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
