package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CartDto;
import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.user.mapper.UserMapper;

public class CartMapper {

    public static CartDto map(CartEntity entity) {
        return new CartDto()
                .setUser(UserMapper.map(entity.getUser()))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal());
    }

}
