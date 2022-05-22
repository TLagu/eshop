package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    ProductEntity getOneByUuid(String uuid);

    @Query("SELECT p FROM ProductEntity p ORDER BY RAND()")
    List<ProductEntity> findRandomForHeader(Pageable pageable);

}
