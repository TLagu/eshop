package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.pagination.Metadata;
import com.lagu.shop.core.pagination.PageWrapper;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.ProductMapper;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductWebController {
    private final static String DEFAULT_PAGE = "0";
    private final static String DEFAULT_SIZE = "6";

    @Autowired
    private ProductService service;

    @GetMapping({"/", "/home"})
    public String slider(Model model) {
        List<ProductEntity> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliderItems", ProductMapper.map(randomForSlider));
        return "product/index.html";
    }

    @GetMapping(value = {"/shop"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            Model model
    ) {
        Map<String, String> params = new HashMap<>();
        ListResponse<ProductDto> allPerPage = service.getAllPerPage(page, size);
        Metadata metadata = allPerPage.getMetadata();
        List<ProductDto> productItems = allPerPage.getContent();
        PageWrapper pageWrapper = new PageWrapper(metadata, "/shop", params);
        List<PageWrapper.PageItem> pageItems = pageWrapper.getPageWrapper();
        model.addAttribute("productItems", productItems);
        model.addAttribute("pageItems", pageItems);
        return "product/products.html";
    }
//
//    @GetMapping(value = "/product/details/{uuid}")
//    public String details(@PathVariable("uuid") String uuid, Model model) {
//            model.addAttribute("products", service.getOne(uuid));
//            return "product/details.html";
//    }
//
//    @GetMapping("/product")
//    public ListResponse<ProductDto> getByPage(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size
//    ) {
//        return service.getAllPerPage(page, size);
//    }
//
//    @PostMapping("/product")
//    public ProductDto create(@RequestBody ProductForm form) {
//        return service.create(form);
//    }
//
//    @PutMapping(value = "/product/{uuid}")
//    public ProductDto update(@PathVariable String uuid,
//                              @RequestBody ProductForm form) {
//        return service.update(uuid, form);
//    }
//
//    @DeleteMapping(value = "/product/{uuid}")
//    public void delete(@PathVariable String uuid) {
//        // TODO: IF NULL
//        // TODO: jak zwrócić KOD 202
//        service.delete(uuid);
//    }
}
