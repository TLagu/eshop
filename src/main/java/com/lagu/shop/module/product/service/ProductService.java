package com.lagu.shop.module.product.service;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.ProductMapper;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto getOne(String uuid) {
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


//    public void createOrUpdate(ProductForm form) {
//        if (form.isNew()) {
//            create(form);
//        } else {
//            update(form.getUuid(), form);
//        }
//    }
//
//    public ProductDto create(ProductForm form) {
//        ProductEntity entity = ProductFormMapper.map(form);
//        ProductEntity updated = productRepository.saveAndFlush(entity);
//        return ProductMapper.map(updated);
//    }
//
//    public ProductDto update(String uuid, ProductForm form) {
//        ProductEntity entity = productRepository.getOneByUuid(uuid)
//                .setModel(form.getModel())
//                .setDescription(form.getDescription());
//        ProductEntity updated = productRepository.saveAndFlush(entity);
//        return ProductMapper.map(updated);
//    }
//
//    public void delete(String uuid) {
//        ProductEntity entity = productRepository.getOneByUuid(uuid);
//        productRepository.delete(entity);
//    }

}
