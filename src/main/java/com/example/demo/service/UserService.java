package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean insertUser(UserDTO userDTO){
        UUID userID = UUID.randomUUID();

        UserModel userToInsert = new UserModel(userID, userDTO.getName(), userDTO.getSurname(),
                userDTO.getAddress(), userDTO.getBirthdate(), userDTO.getBalance());

        return userRepository.insertUser(userToInsert);
    }

}
