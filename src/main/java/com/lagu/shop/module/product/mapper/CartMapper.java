package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CartDto;
import com.lagu.shop.module.product.entity.CartEntity;

public class CartMapper {

    public static CartDto map(CartEntity entity) {
        return new CartDto()
                .setUser(entity.getUser())
                .setProduct(entity.getProduct());
    }

}
