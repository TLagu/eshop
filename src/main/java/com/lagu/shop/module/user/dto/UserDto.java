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
    private Double longitude;
    private Double latitude;
    private String country;
    private String city;
    private String postCode;
    private String post;
    private String street;

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

    public Double getLongitude() {
        return longitude;
    }

    public UserDto setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public UserDto setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserDto setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPost() {
        return post;
    }

    public UserDto setPost(String post) {
        this.post = post;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserDto setStreet(String street) {
        this.street = street;
        return this;
    }

}