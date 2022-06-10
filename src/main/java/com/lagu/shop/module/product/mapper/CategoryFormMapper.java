package com.lagu.shop.module.product.mapper;

import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.dto.TemplateForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.TemplateEntity;
import com.lagu.shop.module.product.entity.Status;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static CategoryForm map(CategoryEntity entity) {
        CategoryForm form = new CategoryForm()
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setParent((entity.getParent() == null) ? null : entity.getParent().getId());
        List<TemplateForm> templates = null;
        if (entity.getTemplates() != null) {
            templates = entity.getTemplates().stream()
                    .map(TemplateFormMapper::map)
                    .collect(Collectors.toList());
        }
        form.setTemplates(templates);
        return form;
    }

}
