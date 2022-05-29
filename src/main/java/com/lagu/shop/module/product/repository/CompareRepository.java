package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.CompareEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CompareRepository extends JpaRepository<CompareEntity, Long>, JpaSpecificationExecutor<CompareEntity> {

    CompareEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    List<CompareEntity> findByUser(UserEntity user);

}
