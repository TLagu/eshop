package com.lagu.shop.module.product.service;

import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.product.entity.TemplateEntity;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final CategoryRepository categoryRepository;

    public TemplateService(TemplateRepository templateRepository, CategoryRepository categoryRepository) {
        this.templateRepository = templateRepository;
        this.categoryRepository = categoryRepository;
    }

    public void delete(Long cid, Long tid) {
        CategoryEntity category = categoryRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii!!!"));
        TemplateEntity template = templateRepository.findByIdAndCategory(tid, category);
        templateRepository.delete(template);
    }

    public void addTemplate(Long cid) {
        CategoryEntity category = categoryRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii!!!"));
        TemplateEntity template = new TemplateEntity()
                .setCategory(category)
                .setStatus(Status.ACTIVE)
                .setCreatedBy(1L)
                .setUpdatedBy(1L);
        templateRepository.saveAndFlush(template);
    }
}
