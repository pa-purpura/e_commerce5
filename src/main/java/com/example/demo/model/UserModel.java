package com.example.demo.model;


import java.util.Date;
import java.util.UUID;

public class UserModel {
    //Attr
    private UUID id;
    private String name;
    private String surname;
    private String address;
    private Date birthdate;
    private double balance;


    //Cost

    public UserModel(UUID id, String name, String surname, String address, Date birthdate, double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthdate = birthdate;
        this.balance = balance;
    }


    //Metodi


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
