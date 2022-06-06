package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.WishlistDto;
import com.lagu.shop.module.product.entity.WishlistEntity;
import com.lagu.shop.module.user.mapper.UserMapper;

public class WishlistMapper {

    public static WishlistDto map(WishlistEntity entity) {
        return new WishlistDto()
                .setUser(UserMapper.map(entity.getUser()))
                .setProduct(ProductMapper.map(entity.getProduct(), null));
    }

}
