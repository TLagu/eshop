package com.lagu.shop.module.product.service;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.product.dto.*;
import com.lagu.shop.module.product.entity.AttributeEntity;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.*;
import com.lagu.shop.module.product.repository.AttributeRepository;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttributeRepository attributeRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
    }

    public ProductDto getByUuid(String uuid) {
        return ProductMapper.map(productRepository.getByUuid(uuid), null);
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getRandomForSlider() {
        return productRepository.findRandomForHeader(PageRequest.of(0, 3)).stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    public ListResponse<ProductDto> getAllPerPage(int page, int size) {
        Page<ProductEntity> pageProduct = productRepository.findAll(PageRequest.of(page, size));
        return new ListResponse<>(
                ProductMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    public ListResponse<ProductDto> getByCategories(long category, int page, int size) {
        List<CategoryEntity> categories = categoryRepository.findAncestry(category);
        Page<ProductEntity> pageProduct = productRepository.findByCategories(categories, PageRequest.of(page, size));
        return new ListResponse<>(
                ProductMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    public void createOrUpdate(ProductForm product) {
        if (product.isNew()) {
            create(product);
        } else {
            update(product.getUuid(), product);
        }
    }

    public ProductDto create(ProductForm productForm) {
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        ProductEntity product = ProductFormMapper.map(productForm, category, null);
        Set<AttributeEntity> attributes = new HashSet<>();
        if (productForm.getAttributes() != null) {
            attributes = productForm.getAttributes().stream()
                    .map(a -> AttributeFormMapper.map(a, product))
                    .collect(Collectors.toSet());
            attributes.forEach(attributeRepository::saveAndFlush);
        }
        product.setAttributes(attributes);
        ProductEntity productUpdate = productRepository.saveAndFlush(product);
        return ProductMapper.map(productUpdate, null);
    }

    public ProductDto update(String uuid, ProductForm productForm) {
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        Set<AttributeEntity> attributes = productForm.getAttributes().stream()
                .map(a -> attributeRepository.findById(a.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono atrybutu do aktualizacji!!!"))
                        .setDescription(a.getDescription())
                )
                .collect(Collectors.toSet());
        ProductEntity product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu do aktualizacji!!!"))
                .setModel(productForm.getModel())
                .setDescription(productForm.getDescription())
                .setCategory(category)
                .setPrice(productForm.getPrice())
                .setCode(productForm.getCode())
                .setAttributes(attributes);
        // TODO: Dlaczego nie działa saveAndFlush razem z Setem
        ProductEntity productUpdated = productRepository.saveAndFlush(product);
        attributes.forEach(attributeRepository::saveAndFlush);
        return ProductMapper.map(productUpdated, null);
    }

    public void delete(String uuid) {
        ProductEntity entity = productRepository.getByUuid(uuid);
        productRepository.delete(entity);
    }

}
