package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.OrderDetailsDto;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.entity.OrderEntity;

import java.util.List;
import java.util.Set;
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
            Set<OrderDetailsDto> orderDetails = null;
            if (entity.getOrderDetails() != null) {
                orderDetails = entity.getOrderDetails().stream()
                        .map(OrderDetailsMapper::map)
                        .collect(Collectors.toSet());
            }
            return order.setOrderDetails(orderDetails);
        }
        return order.setOrderDetails(Set.of(details));
    }

    public static List<OrderDto> map(List<OrderEntity> entities) {
        return entities.stream()
                .map(p -> OrderMapper.map(p, null))
                .collect(Collectors.toList());
    }

}
