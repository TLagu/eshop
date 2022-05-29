package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.WishlistDto;
import com.lagu.shop.module.product.entity.WishlistEntity;

public class WishlistMapper {

    public static WishlistDto map(WishlistEntity entity) {
        return new WishlistDto()
                .setUser(entity.getUser())
                .setProduct(entity.getProduct());
    }

}
