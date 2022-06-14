package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public boolean deleteUser(UUID userID){
        return userRepository.deleteByID(userID);
    }

    public List<UserDTO> getUsersNoID(){
        List<UserModel> listModels = userRepository.getUsers();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserModel userModel : listModels){
            UserDTO userDTO = this.fromModelToDto(userModel);
            if(userDTO!= null){
                userDTOS.add(userDTO);
            }
        }
        return userDTOS;
    }
    public List<UserModel> getUsers(){
        return userRepository.getUsers();
    }

    private UserDTO fromModelToDto(UserModel userModel){
        if(userModel!= null){
            return new UserDTO(userModel.getName(), userModel.getSurname(), userModel.getAddress(),
                    userModel.getBirthdate(), userModel.getBalance());
        }
        return null;
    }

}
