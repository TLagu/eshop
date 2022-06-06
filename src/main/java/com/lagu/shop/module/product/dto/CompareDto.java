package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.user.entity.UserEntity;

public class CompareDto {

    private UserEntity user;

    private ProductDto product;

    public UserEntity getUser() {
        return user;
    }

    public CompareDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public CompareDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }
}
