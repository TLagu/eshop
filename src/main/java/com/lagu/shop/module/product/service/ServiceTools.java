package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.repository.CartRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;

import java.util.function.Consumer;

public class ServiceTools {

    public static void changeAmount(
            String uuid,
            String email,
            Consumer<CartEntity> consumer,
            UserRepository userRepository,
            ProductRepository productRepository,
            CartRepository cartRepository
    ) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user == null || product == null) {
            return;
        }
        CartEntity item = cartRepository.findByUserAndProduct(user, product);
        if (item == null) {
            return;
        }
        consumer.accept(item);
        cartRepository.save(item);
    }

}
