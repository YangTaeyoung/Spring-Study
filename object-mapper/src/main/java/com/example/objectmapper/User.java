package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private  String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;
    // objectMapper를 사용하기 위해서는 디폴트 생성자가 있어야 한다.
    public User(){
        this.name = null;
        this.age = 0;
        this.phoneNumber = null;
    }

    public User(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // objectMapper 사용을 위해서는 getter함수가 있어야 한다.
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
