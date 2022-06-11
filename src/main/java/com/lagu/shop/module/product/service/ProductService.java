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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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

    public ProductDto getDtoByUuid(String uuid) {
        return ProductMapper.map(productRepository.getByUuid(uuid), null);
    }

    public ProductForm getFormByUuid(String uuid) {
        return ProductFormMapper.map(productRepository.getByUuid(uuid));
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

    public ProductEntity createOrUpdate(ProductForm product, MultipartFile multipartFile) throws IOException {
        ProductEntity productEntity = (product.isNew()) ? create(product) : update(product);
        if (!multipartFile.isEmpty()) {
            String fileName = "foto.jpg";
            String uploadDir = "/img/" + productEntity.getId() + "/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            productEntity.setPath(uploadDir + fileName);
            productEntity = productRepository.saveAndFlush(productEntity);
        }
        return productEntity;
    }

    public ProductEntity create(ProductForm productForm) {
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        ProductEntity product = ProductFormMapper.map(productForm, category, null);
        ProductEntity saved = productRepository.saveAndFlush(product);
        Set<AttributeEntity> attributes = null;
        if (productForm.getAttributes() != null) {
            if (productForm.getAttributes() != null) {
                attributes = productForm.getAttributes().stream()
                        .map(a -> AttributeFormMapper.map(a, saved))
                        .collect(Collectors.toSet());
                attributeRepository.deleteAllByProduct(product);
                attributeRepository.flush();
            }
        } else if (category.getTemplates() != null) {
            attributes = category.getTemplates().stream()
                    .map(t -> new AttributeEntity()
                            .setCreatedBy(1L)
                            .setProduct(saved)
                            .setName(t.getName())
                    )
                    .collect(Collectors.toSet());
        }
        product.getAttributes().clear();
        if (attributes != null) {
            product.getAttributes().addAll(attributes);
        }
        return productRepository.saveAndFlush(saved);
    }

    public ProductEntity update(ProductForm productForm) {
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        final ProductEntity product = productRepository.findByUuid(productForm.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu do aktualizacji!!!"));
        Set<AttributeEntity> attributes = null;
        if (Objects.equals(product.getCategory().getId(), productForm.getCategory())) {
            if (productForm.getAttributes() != null && productForm.getAttributes().size() > 0) {
                attributes = productForm.getAttributes().stream()
                        .map(a -> attributeRepository.findById(a.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono atrybutu do aktualizacji!!!"))
                                .setDescription(a.getDescription())
                        )
                        .collect(Collectors.toSet());
            }
        } else if (category.getTemplates() != null) {
            attributes = category.getTemplates().stream()
                    .map(t -> new AttributeEntity()
                            .setCreatedBy(1L)
                            .setProduct(product)
                            .setName(t.getName())
                    )
                    .collect(Collectors.toSet());
            if (product.getAttributes() != null) {
                product.getAttributes().forEach(a -> attributeRepository.deleteById(a.getId()));
            }
        }
        product.setModel(productForm.getModel())
                .setDescription(productForm.getDescription())
                .setCategory(category)
                .setPrice(productForm.getPrice())
                .setCode(productForm.getCode())
                .setAttributes(attributes);
        return productRepository.saveAndFlush(product);
    }

    public void delete(String uuid) {
        ProductEntity entity = productRepository.getByUuid(uuid);
        productRepository.delete(entity);
    }

    public List<ProductDto> searchProducts(String search) {
        List<ProductEntity> products = productRepository.searchProduct(search);
        return products.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

}
