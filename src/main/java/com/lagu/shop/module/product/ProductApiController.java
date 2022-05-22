package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.mapper.ProductMapper;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductApiController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/api/product/{guid}")
    public ProductDto getOne(@PathVariable String guid) {
        // TODO: IF NULL
        return ProductMapper.map(service.getOne(guid), null);
    }

    @GetMapping("/api/product")
    public ListResponse<ProductDto> getByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        return service.getAllPerPage(page, size);
    }

//    @PostMapping("/api/product")
//    public ProductDto create(@RequestBody ProductForm form) {
//        return service.create(form);
//    }
//
//    @PutMapping(value = "/api/product/{uuid}")
//    public ProductDto update(@PathVariable String uuid,
//                                @RequestBody ProductForm form) {
//        return service.update(uuid, form);
//    }
//
//    @DeleteMapping(value = "/api/product/{uuid}")
//    public void delete(@PathVariable String uuid) {
//        // TODO: IF NULL
//        service.delete(uuid);
//    }
}
