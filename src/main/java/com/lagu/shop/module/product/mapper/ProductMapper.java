package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto map(ProductEntity entity) {
//        ProductDto result = new ProductDto()
//                .setUuid(entity.getUuid())
//                .setModel(entity.getModel())
//                .setDescription(entity.getDescription())
//                .setCategory(CategoryMapper.map(entity.getCategory()));
//        result.setAttributes(entity.getAttributes().stream()
//                .map(a -> AttributeMapper.map(a, result))
//                .collect(Collectors.toSet()));
//        return result;
        return new ProductDto()
                .setUuid(entity.getUuid())
                .setModel(entity.getModel())
                .setDescription(entity.getDescription())
                .setCategory(CategoryMapper.map(entity.getCategory()));
    }

    public static List<ProductDto> map(List<ProductEntity> entities) {
        return entities.stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList());
    }

}
