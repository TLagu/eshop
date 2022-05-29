package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.repository.CartRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public void add(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getOneByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item == null) {
                CartEntity tmpItem = new CartEntity().setUser(user).setProduct(product);
                cartRepository.save(tmpItem);
            }
        }
    }

    public void remove(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getOneByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item != null) {
                cartRepository.delete(item);
            }
        }
    }

    public Set<String> getProductListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        Set<String> items = new HashSet<>();
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUser(user);
            if (byUser != null) {
                items = byUser.stream().map(c -> c.getProduct().getUuid()).collect(Collectors.toSet());
            }
        }
        return items;
    }
}
