package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            System.out.println(userDTO.getBirthdate());
            boolean inserted = userService.insertUser(userDTO);
            if(inserted){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        System.out.println("no");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
