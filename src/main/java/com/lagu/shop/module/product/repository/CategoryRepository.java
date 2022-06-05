package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

    List<CategoryEntity> findByParentIsNullOrderByName();

    List<CategoryEntity> findByParentIsNullAndIdIsNotOrderByName(Long id);

    @Query(
            value = "WITH RECURSIVE" +
                    "  starting (id, created_on, created_by, updated_on, updated_by, status, name, description, parent_id) AS (" +
                    "    SELECT t.id, t.created_on, t.created_by, t.updated_on, t.updated_by, t.status, t.name, t.description, t.parent_id" +
                    "    FROM category AS t" +
                    "    WHERE t.id = :categoryId" +
                    "  )," +
                    "  descendants (id, created_on, created_by, updated_on, updated_by, status, name, description, parent_id) AS (" +
                    "    SELECT s.id, s.created_on, s.created_by, s.updated_on, s.updated_by, s.status, s.name, s.description, s.parent_id" +
                    "    FROM starting AS s" +
                    "    UNION ALL" +
                    "    SELECT t.id, t.created_on, t.created_by, t.updated_on, t.updated_by, t.status, t.name, t.description, t.parent_id" +
                    "    FROM category AS t JOIN descendants AS d ON t.parent_id = d.id" +
                    "  )" +
                    "  TABLE descendants",
            nativeQuery = true)
    List<CategoryEntity> findAncestry(@Param("categoryId") Long categoryId);

}
