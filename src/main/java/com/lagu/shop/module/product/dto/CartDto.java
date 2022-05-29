package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.user.entity.UserEntity;

public class CartDto {

    private UserEntity user;

    private ProductEntity product;

    public UserEntity getUser() {
        return user;
    }

    public CartDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CartDto setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

}
