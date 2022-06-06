package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CartForm;
import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.user.mapper.UserMapper;

public class CartFormMapper {

    public static CartForm map(CartEntity entity) {
        return new CartForm()
                .setUser(UserMapper.map(entity.getUser()))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal())
                .setUuid(entity.getProduct().getUuid())
                .setModel(entity.getProduct().getModel())
                .setCode(entity.getProduct().getCode())
                .setPrice(entity.getProduct().getPrice())
                .setPath(entity.getProduct().getPath());
    }

}
