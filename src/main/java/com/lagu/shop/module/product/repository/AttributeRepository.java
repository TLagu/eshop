package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.AttributeEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {

    void deleteAllByProduct(ProductEntity product);
}
