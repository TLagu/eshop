package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.entity.WishlistEntity;
import com.lagu.shop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long>, JpaSpecificationExecutor<WishlistEntity> {

    WishlistEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    List<WishlistEntity> findByUser(UserEntity user);

}
