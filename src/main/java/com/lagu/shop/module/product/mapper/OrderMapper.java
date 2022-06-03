package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.OrderDetailsDto;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.entity.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto map(OrderEntity entity, OrderDetailsDto details) {
        OrderDto order = new OrderDto()
                .setCreatedOn(entity.getCreatedOn())
                .setUuid(entity.getUuid())
                .setUser(entity.getUser())
                .setStreet(entity.getStreet())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setTotal(entity.getTotal());
        if (details == null) {
            return order.setOrderDetails(entity.getOrderDetails().stream()
                    .map(OrderDetailsMapper::map)
                    .collect(Collectors.toList())
            );
        }
        return order.setOrderDetails(List.of(details));
    }

}
