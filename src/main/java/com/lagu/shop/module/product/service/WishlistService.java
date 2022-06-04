package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.entity.WishlistEntity;
import com.lagu.shop.module.product.repository.ProductRepository;
import com.lagu.shop.module.product.repository.WishlistRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public void add(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            WishlistEntity item = wishlistRepository.findByUserAndProduct(user, product);
            if (item == null) {
                WishlistEntity tmpItem = new WishlistEntity().setUser(user).setProduct(product);
                wishlistRepository.save(tmpItem);
            }
        }
    }

    public void remove(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            WishlistEntity item = wishlistRepository.findByUserAndProduct(user, product);
            if (item != null) {
                wishlistRepository.delete(item);
            }
        }
    }

    public Set<String> getProductListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        Set<String> items = new HashSet<>();
        if (user != null) {
            List<WishlistEntity> byUser = wishlistRepository.findByUser(user);
            if (byUser != null) {
                items = byUser.stream().map(c -> c.getProduct().getUuid()).collect(Collectors.toSet());
            }
        }
        return items;
    }
}
