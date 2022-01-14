package com.example.myfitnessapp;

import android.widget.EditText;

public class User {
    String fullname, username, email,password, gender;
    double age, weight, height;

    public User(String fullname, String username, String email, String password) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String fullname, String username, String email, String password, String gender, double age, double weight, double height) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public User(String gender, double age, double weight, double height) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    public User(){

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
