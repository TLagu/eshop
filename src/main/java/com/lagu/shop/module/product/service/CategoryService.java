package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.dto.CategoryDto;
import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.TemplateEntity;
import com.lagu.shop.module.product.mapper.CategoryFormMapper;
import com.lagu.shop.module.product.mapper.CategoryMapper;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TemplateRepository templateRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           TemplateRepository templateRepository) {
        this.categoryRepository = categoryRepository;
        this.templateRepository = templateRepository;
    }

    public List<CategoryEntity> getMainCategoryWithSubcategories() {
        return categoryRepository.findByParentIsNullOrderByName();
    }

    public List<CategoryDto> getAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    public CategoryDto getDtoById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return CategoryMapper.map(entity.orElse(null), null);
    }

    public CategoryForm getFormById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return (entity.isEmpty()) ? null : CategoryFormMapper.map(entity.get());
    }

    public List<CategoryDto> getByParentIsNull() {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    public List<CategoryDto> getByParentIsNullAndIdNot(Long id) {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullAndIdIsNotOrderByName(id);
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    public CategoryEntity createOrUpdate(CategoryForm category) {
        if (category.isNew()) {
            return create(category);
        }
        return update(category.getId(), category);
    }

    public CategoryEntity create(CategoryForm categoryForm) {
        Optional<CategoryEntity> parent = categoryRepository.findById(categoryForm.getParent());
        Set<TemplateEntity> templates = new HashSet<>();
        CategoryEntity categoryEntity = CategoryFormMapper.map(categoryForm, parent.orElse(null), templates);
        return categoryRepository.saveAndFlush(categoryEntity);
    }

    public CategoryEntity update(Long id, CategoryForm categoryForm) {
        CategoryEntity parent = categoryRepository.findById(categoryForm.getParent()).orElse(null);
        Set<TemplateEntity> templates = null;
        if (categoryForm.getTemplates() != null) {
            templates = categoryForm.getTemplates().stream()
                    .map(c -> templateRepository.findById(c.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono parametru do aktualizacji!!!"))
                            .setName(c.getName())
                    )
                    .collect(Collectors.toSet());
        }
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii do aktualizacji!!!"))
                .setName(categoryForm.getName())
                .setDescription(categoryForm.getDescription())
                .setParent(parent)
                .setTemplates(templates);
        return categoryRepository.saveAndFlush(category);
    }

    public void delete(Long id) {
        CategoryEntity entity = categoryRepository.getById(id);
        categoryRepository.delete(entity);
    }

}
