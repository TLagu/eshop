package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.Status;

public class CategoryFormMapper {

    public static CategoryEntity map(CategoryForm form, CategoryEntity parent) {
        return new CategoryEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setDescription(form.getDescription())
                .setParent(parent);
    }

}
