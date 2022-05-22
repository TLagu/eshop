package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CategoryDto;
import com.lagu.shop.module.product.entity.CategoryEntity;

public class CategoryMapper {

    public static CategoryDto map(CategoryEntity entity) {
        return new CategoryDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
