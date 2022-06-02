package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.OrderDetailsDto;
import com.lagu.shop.module.product.entity.OrderDetailsEntity;

public class OrderDetailsMapper {

    public static OrderDetailsDto map(OrderDetailsEntity entity) {
        return new OrderDetailsDto()
                .setOrder(entity.getOrder())
                .setProduct(entity.getProduct())
                .setPrice(entity.getPrice())
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal());
    }

}
