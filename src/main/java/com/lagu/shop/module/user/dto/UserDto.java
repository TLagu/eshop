package com.lagu.shop.module.user.dto;

import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.user.entity.UserRole;

public class UserDto {

    private Status status = Status.ACTIVE;
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role = UserRole.USER;

    public Status getStatus() {
        return status;
    }

    public UserDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UserDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public UserDto setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}