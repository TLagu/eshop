package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.CartEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {

    CartEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    List<CartEntity> findByUser(UserEntity user);

}
