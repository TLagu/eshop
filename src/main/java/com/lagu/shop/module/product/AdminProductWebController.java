package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.core.pagination.PageWrapper;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.dto.ProductForm;
import com.lagu.shop.module.product.entity.ProductEntity;
import com.lagu.shop.module.product.service.CategoryService;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
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
        model.addAttribute("product", service.getDtoByUuid(uuid));
        return "shop/admin-product-details";
    }

    @GetMapping("/admin/product-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

    @GetMapping("/admin/product/{uuid}/edit")
    public String edit(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", service.getFormByUuid(uuid));
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

    @PostMapping("/admin/product")
    public String createOrUpdate(@Valid @ModelAttribute("productForm") ProductForm productForm,
                                 BindingResult result, Model model, HttpServletRequest request,
                                 @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
        if (result.hasErrors()) {
            setCommonAttributes(request, model);
            model.addAttribute("categories", categoryService.getAll());
            return "shop/admin-product-form";
        }
        ProductEntity productEntity = service.createOrUpdate(productForm, multipartFile);
        return "redirect:/admin/product/" + productEntity.getUuid() + "/edit";
    }

    @GetMapping("/admin/product/{uuid}/delete")
    public String deleteByGet(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
    }

    @DeleteMapping("/shop/product/{uuid}/delete")
    public String deleteByPost(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
    }

    private void setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
    }

}
