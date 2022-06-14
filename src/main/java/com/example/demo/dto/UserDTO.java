package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;


public class UserDTO {
    //Attr
    private String name;
    private String surname;
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;
    private double balance;


    //Cost

    public UserDTO(String name, String surname, String address, Date birthdate, double balance) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthdate = birthdate;
        this.balance = balance;
    }


    //Metodi

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
