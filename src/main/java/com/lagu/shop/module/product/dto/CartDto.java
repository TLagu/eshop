package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.user.dto.UserDto;

public class CartDto {

    private UserDto user;

    private ProductDto product;

    private Integer amount;

    private Double total;

    public UserDto getUser() {
        return user;
    }

    public CartDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public CartDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public CartDto setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public CartDto setTotal(Double total) {
        this.total = total;
        return this;
    }
}
