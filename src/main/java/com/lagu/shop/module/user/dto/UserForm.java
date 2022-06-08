package com.lagu.shop.module.user.dto;

public class UserForm {

    private String status;
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private Double longitude;
    private Double latitude;
    private String country;
    private String city;
    private String postCode;
    private String post;
    private String street;

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

    public Double getLongitude() {
        return longitude;
    }

    public UserForm setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public UserForm setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserForm setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserForm setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserForm setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPost() {
        return post;
    }

    public UserForm setPost(String post) {
        this.post = post;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserForm setStreet(String street) {
        this.street = street;
        return this;
    }

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}