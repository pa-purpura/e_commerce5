package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertUser(UserModel userModel){
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO \"user\" (id, name, surname, address, birthdate, balance) VALUES (?,?,?,?,?,?)",
                userModel.getId(),
                userModel.getName(),
                userModel.getSurname(),
                userModel.getAddress(),
                userModel.getBirthdate(),
                userModel.getBalance());
        return rowsAffected > 0;
    }

    public boolean deleteByID(UUID userID){
        int rowsAffected = jdbcTemplate.update(
                "delete from \"user\" where id=?",
                userID);
        return rowsAffected > 0;

    }

    public List<UserModel> getUsers(){

        return jdbcTemplate.query("select * from \"user\";",
                (rs, rowNum) ->
                        new UserModel(
                                (UUID) rs.getObject("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("address"),
                                rs.getDate("birthdate"),
                                rs.getDouble("balance")
                        )
        );
    }

}
