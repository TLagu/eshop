package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.TemplateEntity;
import com.lagu.shop.module.product.entity.Status;

import java.util.Set;

public class CategoryFormMapper {

    public static CategoryEntity map(CategoryForm form, CategoryEntity parent,
                                     Set<TemplateEntity> templates) {
        return new CategoryEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setDescription(form.getDescription())
                .setParent(parent)
                .setTemplates(templates);
    }

}
