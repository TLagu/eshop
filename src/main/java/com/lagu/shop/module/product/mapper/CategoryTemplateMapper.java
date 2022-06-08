package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.TemplateDto;
import com.lagu.shop.module.product.entity.TemplateEntity;

public class CategoryTemplateMapper {

    public static TemplateDto map(TemplateEntity entity) {
        return new TemplateDto()
                .setId(entity.getId())
                .setCategory(CategoryMapper.map(entity.getCategory(), entity))
                .setName(entity.getName());
    }

}
