package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.AttributeDto;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.AttributeEntity;

public class AttributeMapper {

    public static AttributeDto map(AttributeEntity entity) {
        return new AttributeDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
