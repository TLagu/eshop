package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.*;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.mapper.ProductMapper;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductWebController {
    private final static String DEFAULT_PAGE = "0";
    private final static String DEFAULT_SIZE = "6";
    @Autowired
    private ProductService service;

    @GetMapping({"/", "/home", "/logged", "/logged/home"})
    public String slider(
            Model model,
            HttpServletRequest request
    ) {
        String prefix = (request.getRequestURI().contains("logged")) ? "/logged" : "";
        List<ProductEntity> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliderItems", ProductMapper.map(randomForSlider));
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu("/", prefix));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(prefix));
        return "shop/index.html";
    }

    @GetMapping(value = {"/shop", "/logged/shop"})
    public String loggedOutList(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) int size,
            Model model,
            HttpServletRequest request
    ) {
        String prefix = (request.getRequestURI().contains("logged")) ? "/logged" : "";
        Map<String, String> params = new HashMap<>();
        ListResponse<ProductDto> allPerPage = service.getAllPerPage(page, size);
        Metadata metadata = allPerPage.getMetadata();
        List<ProductDto> productItems = allPerPage.getContent();
        PageWrapper pageWrapper = new PageWrapper(metadata, prefix + "/shop", params);
        List<PageWrapper.PageItem> pageItems = pageWrapper.getPageWrapper();
        model.addAttribute("productItems", productItems);
        model.addAttribute("pageItems", pageItems);
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu("/shop", prefix));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(prefix));
        return "shop/product.html";
    }

//
//    @GetMapping(value = "/shop/details/{uuid}")
//    public String details(@PathVariable("uuid") String uuid, Model model) {
//            model.addAttribute("products", service.getOne(uuid));
//            return "shop/details.html";
//    }
//
//    @GetMapping("/shop")
//    public ListResponse<ProductDto> getByPage(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size
//    ) {
//        return service.getAllPerPage(page, size);
//    }
//
//    @PostMapping("/shop")
//    public ProductDto create(@RequestBody ProductForm form) {
//        return service.create(form);
//    }
//
//    @PutMapping(value = "/shop/{uuid}")
//    public ProductDto update(@PathVariable String uuid,
//                              @RequestBody ProductForm form) {
//        return service.update(uuid, form);
//    }
//
//    @DeleteMapping(value = "/shop/{uuid}")
//    public void delete(@PathVariable String uuid) {
//        // TODO: IF NULL
//        // TODO: jak zwrócić KOD 202
//        service.delete(uuid);
//    }
}
