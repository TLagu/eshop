package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private LocalDateTime createdOn;

    private String uuid;

    private UserEntity user;

    private String street;

    private String postCode;

    private String post;

    private Double total;

    private List<OrderDetailsDto> orderDetails;

    public String getUuid() {
        return uuid;
    }

    public OrderDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderDto setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public OrderDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public OrderDto setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPost() {
        return post;
    }

    public OrderDto setPost(String post) {
        this.post = post;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderDto setTotal(Double total) {
        this.total = total;
        return this;
    }

    public List<OrderDetailsDto> getOrderDetails() {
        return orderDetails;
    }

    public OrderDto setOrderDetails(List<OrderDetailsDto> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }
}
