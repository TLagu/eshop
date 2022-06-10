package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.AttributeForm;
import com.lagu.shop.module.product.entity.AttributeEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.entity.Status;

public class AttributeFormMapper {

    public static AttributeEntity map(AttributeForm form, ProductEntity product) {
        return new AttributeEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setProduct(product)
                .setName(form.getName())
                .setDescription(form.getDescription());
    }

    public static AttributeForm map (AttributeEntity entity) {
        return new AttributeForm()
                .setId(entity.getId())
                .setProduct(entity.getProduct().getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
