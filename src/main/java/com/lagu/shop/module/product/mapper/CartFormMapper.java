package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CartFormDto;
import com.lagu.shop.module.product.entity.CartEntity;

public class CartFormMapper {

    public static CartFormDto map(CartEntity entity) {
        return new CartFormDto()
                .setUser(entity.getUser())
                .setProduct(entity.getProduct())
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal())
                .setUuid(entity.getProduct().getUuid())
                .setModel(entity.getProduct().getModel())
                .setCode(entity.getProduct().getCode())
                .setPrice(entity.getProduct().getPrice())
                .setPath(entity.getProduct().getPath());
    }

}
