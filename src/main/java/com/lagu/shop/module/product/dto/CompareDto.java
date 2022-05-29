package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.user.entity.UserEntity;

import java.time.LocalDateTime;

public class CompareDto {

    private UserEntity user;

    private ProductEntity product;

    public UserEntity getUser() {
        return user;
    }

    public CompareDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CompareDto setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }
}
