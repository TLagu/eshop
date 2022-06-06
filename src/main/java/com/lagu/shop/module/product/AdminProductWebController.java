package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.core.pagination.PageWrapper;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.service.CategoryService;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminProductWebController {

    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "25";

    private final ProductService service;
    private final CategoryService categoryService;

    public AdminProductWebController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping({"/admin", "/admin/product"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            Model model,
            HttpServletRequest request
    ) {
        setCommonAttributes(request, model);
        ListResponse<ProductDto> allPerPage = service.getAllPerPage(page, size);
        PageWrapper pageWrapper = new PageWrapper(allPerPage.getMetadata(), request.getRequestURI(), new HashMap<>());
        List<ProductDto> products = allPerPage.getContent();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        return "shop/admin-product-list";
    }

    @GetMapping("/admin/product/{uuid}")
    public String details(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("product", service.getByUuid(uuid));
        return "shop/admin-product-details";
    }

    @GetMapping("/admin/product-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", new ProductDto());
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

    @GetMapping("/admin/product/{uuid}/edit")
    public String edit(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", service.getByUuid(uuid));
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

//    @PostMapping("/admin/product")
//    public String createOrUpdate(ProductDto product) {
//        service.createOrUpdate(product);
//        return "redirect:/shop/admin-product-list";
//    }

    @GetMapping("/admin/product/{uuid}/delete")
    public String deleteByGet(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
    }

    @PostMapping("/shop/product/{uuid}/delete")
    public String deleteByPost(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
    }

    private Model setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
        return model;
    }

}
