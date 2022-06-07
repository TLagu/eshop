package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CompareDto;
import com.lagu.shop.module.product.entity.CompareEntity;

public class CompareMapper {

    public static CompareDto map(CompareEntity entity) {
        return new CompareDto()
                .setUser(entity.getUser())
                .setProduct(ProductMapper.map(entity.getProduct(), null));
    }

}
