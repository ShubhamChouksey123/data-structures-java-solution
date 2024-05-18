package com.shubham.app.serialization;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private UUID userId;
    private String personalName;
    private String lastName;
    private Integer age;

    public User(UUID userId, String personalName, String lastName, Integer age) {
        this.userId = userId;
        this.personalName = personalName;
        this.lastName = lastName;
        this.age = age;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", personalName='" + personalName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
