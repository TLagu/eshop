package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.TemplateForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.TemplateEntity;
import com.lagu.shop.module.product.entity.Status;

public class CategoryTemplateFormMapper {

    public static TemplateEntity map(TemplateForm form, CategoryEntity category) {
        return new TemplateEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setCategory(category);

    }

}
