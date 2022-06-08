package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.AttributeDto;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.dto.ProductDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto map(ProductEntity entity, AttributeDto attribute) {
        ProductDto result = new ProductDto()
                .setUuid(entity.getUuid())
                .setModel(entity.getModel())
                .setDescription(entity.getDescription())
                .setCategory(CategoryMapper.map(entity.getCategory(), null))
                .setPrice(entity.getPrice())
                .setPath(entity.getPath())
                .setCode(entity.getCode());
        if (attribute == null) {
            return result.setAttributes(entity.getAttributes().stream()
                    .map(AttributeMapper::map)
                    .collect(Collectors.toSet()));
        }
        return result.setAttributes(Set.of(attribute));
    }

    public static List<ProductDto> map(List<ProductEntity> entities) {
        return entities.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

}
