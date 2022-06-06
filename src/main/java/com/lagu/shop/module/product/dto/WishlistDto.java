package com.lagu.shop.module.product.dto;

import com.lagu.shop.module.user.dto.UserDto;

public class WishlistDto {

    private UserDto user;

    private ProductDto product;

    public UserDto getUser() {
        return user;
    }

    public WishlistDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public WishlistDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }
}
