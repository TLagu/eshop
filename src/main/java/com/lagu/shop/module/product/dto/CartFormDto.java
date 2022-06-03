package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.user.entity.UserEntity;

public class CartFormDto {

    private UserEntity user;

    private ProductEntity product;

    private Integer amount;

    private Double total;

    private String uuid;

    private String model;

    private String code;

    private Double price;

    private String path;

    public UserEntity getUser() {
        return user;
    }

    public CartFormDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CartFormDto setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public CartFormDto setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public CartFormDto setTotal(Double total) {
        this.total = total;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public CartFormDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CartFormDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getCode() {
        return code;
    }

    public CartFormDto setCode(String code) {
        this.code = code;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public CartFormDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getPath() {
        return path;
    }

    public CartFormDto setPath(String path) {
        this.path = path;
        return this;
    }
}
