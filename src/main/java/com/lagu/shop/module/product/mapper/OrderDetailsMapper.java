package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.OrderDetailsDto;
import com.lagu.shop.module.product.entity.OrderDetailsEntity;

public class OrderDetailsMapper {

    public static OrderDetailsDto map(OrderDetailsEntity entity) {
        return new OrderDetailsDto()
                .setOrder(OrderMapper.map(entity.getOrder(), null))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setPrice(entity.getPrice())
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal());
    }

}
