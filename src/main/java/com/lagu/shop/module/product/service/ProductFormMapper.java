package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.ProductForm;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.entity.Status;

import java.util.UUID;

public class ProductFormMapper {
    public static ProductEntity map(ProductForm form) {
        return new ProductEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setModel(form.getModel())
                .setDescription(form.getDescription());
    }
}
