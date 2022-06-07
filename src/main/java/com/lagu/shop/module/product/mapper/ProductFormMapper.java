package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.ProductForm;
import com.lagu.shop.module.product.entity.AttributeEntity;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.entity.Status;

import java.util.Set;
import java.util.UUID;

public class ProductFormMapper {

    public static ProductEntity map(ProductForm form, CategoryEntity category, Set<AttributeEntity> attributes) {
        return new ProductEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setModel(form.getModel())
                .setDescription(form.getDescription())
                .setCategory(category)
                .setPrice(form.getPrice())
                .setCode(form.getCode())
                .setAttributes(attributes);
    }

}
