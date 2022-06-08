package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Long>, JpaSpecificationExecutor<TemplateEntity> {

    Set<TemplateEntity> findByIdIn(List<Long> ids);
    TemplateEntity findByIdAndCategory(Long id, CategoryEntity category);

}
