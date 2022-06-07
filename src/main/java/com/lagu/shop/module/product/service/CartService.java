package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.CartForm;
import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.CartFormMapper;
import com.lagu.shop.module.product.repository.CartRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void add(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item == null) {
                CartEntity tmpItem = new CartEntity()
                        .setUser(user)
                        .setProduct(product)
                        .setAmount(1)
                        .setTotal(product.getPrice());
                cartRepository.save(tmpItem);
            }
        }
    }

    public void remove(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item != null) {
                cartRepository.delete(item);
            }
        }
    }

    public void removeAmount(String uuid, String email) {
        ServiceTools.changeAmount(uuid, email, CartEntity::removeAmount, userRepository, productRepository, cartRepository);
    }

    public void addAmount(String uuid, String email) {
        ServiceTools.changeAmount(uuid, email, CartEntity::addAmount, userRepository, productRepository, cartRepository);
    }

    public Set<String> getProductUuidListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        Set<String> items = new HashSet<>();
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                items = byUser.stream().map(c -> c.getProduct().getUuid()).collect(Collectors.toSet());
            }
        }
        return items;
    }

    public List<CartForm> getProductListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        List<CartForm> items = new ArrayList<>();
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                items = byUser.stream()
                        .map(CartFormMapper::map)
                        .collect(Collectors.toList());
            }
        }
        return items;
    }

}
