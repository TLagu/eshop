package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getMainCategoryWithSubcategories() {
        return categoryRepository.findByParentIsNullOrderByName();
    }

}
