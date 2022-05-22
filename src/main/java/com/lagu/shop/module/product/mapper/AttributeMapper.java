package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.AttributeDto;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.AttributeEntity;

public class AttributeMapper {

    public static AttributeDto map(AttributeEntity entity, ProductDto productDto) {
            return new AttributeDto()
                    .setProduct((productDto == null) ? (ProductMapper.map(entity.getProduct())) : productDto)
                    .setId(entity.getId())
                    .setName(entity.getName())
                    .setDescription(entity.getDescription());
    }

}
