package com.lagu.shop.module.product.service;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.ProductMapper;
import com.lagu.shop.module.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity getOne(String uuid) {
        return productRepository.getOneByUuid(uuid);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> getRandomForSlider() {
        return productRepository.findRandomForHeader(PageRequest.of(0,3));
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
