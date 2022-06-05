package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.CompareEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.repository.CompareRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompareService {

    private final CompareRepository compareRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CompareService(CompareRepository compareRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.compareRepository = compareRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void add(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CompareEntity item = compareRepository.findByUserAndProduct(user, product);
            if (item == null) {
                CompareEntity tmpItem = new CompareEntity().setUser(user).setProduct(product);
                compareRepository.save(tmpItem);
            }
        }
    }

    public void remove(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CompareEntity item = compareRepository.findByUserAndProduct(user, product);
            if (item != null) {
                compareRepository.delete(item);
            }
        }
    }

    public Set<String> getProductListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        Set<String> items = new HashSet<>();
        if (user != null) {
            List<CompareEntity> byUser = compareRepository.findByUser(user);
            if (byUser != null) {
                items = byUser.stream().map(c -> c.getProduct().getUuid()).collect(Collectors.toSet());
            }
        }
        return items;
    }
}
