package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    ProductEntity getByUuid(String uuid);

    Optional<ProductEntity> findByUuid(String uuid);

    @Query("SELECT p FROM ProductEntity p ORDER BY RAND()")
    List<ProductEntity> findRandomForHeader(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.category IN :ids")
    Page<ProductEntity> findByCategories(List<CategoryEntity> ids, Pageable pageable);

}
