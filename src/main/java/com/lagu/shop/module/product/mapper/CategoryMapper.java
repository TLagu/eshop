package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CategoryDto;
import com.lagu.shop.module.product.entity.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDto map(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CategoryDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setParent(CategoryMapper.map(entity.getParent()));
    }

    public static List<CategoryDto> map(List<CategoryEntity> entities) {
        return entities.stream()
                .map(CategoryMapper::map)
                .collect(Collectors.toList());
    }

}
