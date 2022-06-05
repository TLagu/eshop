package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.CategoryDto;
import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.mapper.CategoryFormMapper;
import com.lagu.shop.module.product.mapper.CategoryMapper;
import com.lagu.shop.module.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getMainCategoryWithSubcategories() {
        return categoryRepository.findByParentIsNullOrderByName();
    }

    public List<CategoryDto> getAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::map).collect(Collectors.toList());
    }

    public CategoryDto getById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return CategoryMapper.map(entity.orElse(null));
    }

    public List<CategoryDto> getByParentIsNull() {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        return categories.stream().map(CategoryMapper::map).collect(Collectors.toList());
    }

    public List<CategoryDto> getByParentIsNullAndIdNot(Long id) {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullAndIdIsNotOrderByName(id);
        return categories.stream().map(CategoryMapper::map).collect(Collectors.toList());
    }

    public void createOrUpdate(CategoryForm category) {
        if (category.isNew()) {
            create(category);
        } else {
            update(category.getId(), category);
        }
    }

    public CategoryDto create(CategoryForm categoryForm) {
        Optional<CategoryEntity> parent = categoryRepository.findById(categoryForm.getParent());
        CategoryEntity categoryEntity = CategoryFormMapper.map(categoryForm, parent.orElse(null));
        CategoryEntity categoryUpdate = categoryRepository.saveAndFlush(categoryEntity);
        return CategoryMapper.map(categoryUpdate);
    }

    public CategoryDto update(Long id, CategoryForm categoryForm) {
        CategoryEntity parent = categoryRepository.findById(categoryForm.getParent()).orElse(null);
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii do aktualizacji!!!"))
                .setName(categoryForm.getName())
                .setDescription(categoryForm.getDescription())
                .setParent(parent);
        CategoryEntity categoryUpdated = categoryRepository.saveAndFlush(category);
        return CategoryMapper.map(categoryUpdated);
    }

    public void delete(Long id) {
        CategoryEntity entity = categoryRepository.getById(id);
        categoryRepository.delete(entity);
    }

}
