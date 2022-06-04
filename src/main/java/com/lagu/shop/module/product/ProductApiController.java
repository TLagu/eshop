package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductApiController {

    private final ProductService service;

    public ProductApiController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/shop/{guid}")
    public ProductDto getOne(@PathVariable String guid) {
        if (guid == null) {
            return null;
        }
        return service.getOne(guid);
    }

    @GetMapping("/api/shop")
    public ListResponse<ProductDto> getByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        return service.getAllPerPage(page, size);
    }

//    @PostMapping("/api/shop")
//    public ProductDto create(@RequestBody ProductForm form) {
//        return service.create(form);
//    }
//
//    @PutMapping(value = "/api/shop/{uuid}")
//    public ProductDto update(@PathVariable String uuid,
//                                @RequestBody ProductForm form) {
//        return service.update(uuid, form);
//    }
//
//    @DeleteMapping(value = "/api/shop/{uuid}")
//    public void delete(@PathVariable String uuid) {
//        // TODO: IF NULL
//        service.delete(uuid);
//    }
}
