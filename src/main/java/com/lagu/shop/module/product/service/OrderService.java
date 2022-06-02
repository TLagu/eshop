package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.product.entity.OrderDetailsEntity;
import com.lagu.shop.module.product.entity.OrderEntity;
import com.lagu.shop.module.product.mapper.OrderMapper;
import com.lagu.shop.module.product.repository.CartRepository;
import com.lagu.shop.module.product.repository.OrderDetailsRepository;
import com.lagu.shop.module.product.repository.OrderRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDto getInitialOrder(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderDto order = new OrderDto().setUser(user).setTotal(0.0);
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                Double sum = byUser.stream().map(CartEntity::getTotal).reduce(0.0, Double::sum);
                order.setTotal(sum);
            }
        }
        return order;
    }

    public boolean saveOrder(OrderDto orderDto, Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
        if (user != null && byUser != null && byUser.size() > 0) {
            Double sum = byUser.stream().map(CartEntity::getTotal).reduce(0.0, Double::sum);
            OrderEntity order = new OrderEntity()
                    .setUuid(UUID.randomUUID().toString())
                    .setUser(user)
                    .setStreet(orderDto.getStreet())
                    .setPostCode(orderDto.getPostCode())
                    .setPost(orderDto.getPost())
                    .setTotal(sum);
            OrderEntity savedOrder = orderRepository.save(order);
            List<OrderDetailsEntity> orderDetails = byUser.stream()
                    .map(c -> new OrderDetailsEntity()
                            .setOrder(savedOrder)
                            .setProduct(c.getProduct())
                            .setPrice(c.getProduct().getPrice())
                            .setAmount(c.getAmount())
                            .setTotal(c.getTotal()))
                    .collect(Collectors.toList());
            orderDetailsRepository.saveAll(orderDetails);
            return true;
        }
        return false;
    }

    public List<OrderDto> getUserOrders(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        List<OrderEntity> orders = orderRepository.findByUserOrderByCreatedOn(user);
        return orders.stream()
                .map(e -> OrderMapper.map(e, null))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderByUuid(Authentication authentication, String uuid) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderEntity order = orderRepository.getByUserAndUuid(user, uuid);
        return OrderMapper.map(order, null);
    }

}
