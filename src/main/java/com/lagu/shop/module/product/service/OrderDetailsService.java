package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.OrderDetailsDto;
import com.lagu.shop.module.product.entity.OrderEntity;
import com.lagu.shop.module.product.mapper.OrderDetailsMapper;
import com.lagu.shop.module.product.repository.OrderDetailsRepository;
import com.lagu.shop.module.product.repository.OrderRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDetailsDto> getOrders(Authentication authentication, String uuid) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderEntity order = orderRepository.getByUserAndUuid(user, uuid);
        return orderDetailsRepository.findByOrderOrderByProduct(order).stream()
                .map(OrderDetailsMapper::map)
                .collect(Collectors.toList());
    }

}
