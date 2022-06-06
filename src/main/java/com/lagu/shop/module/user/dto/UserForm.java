package com.lagu.shop.module.user.dto;

public class UserForm {

    private String status;
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public String getStatus() {
        return status;
    }

    public UserForm setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UserForm setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserForm setRole(String role) {
        this.role = role;
        return this;
    }

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}