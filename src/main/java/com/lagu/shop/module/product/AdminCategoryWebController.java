package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.dto.CategoryDto;
import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminCategoryWebController {

    private final CategoryService service;

    public AdminCategoryWebController(CategoryService service) {
        this.service = service;
    }

    @GetMapping({"/admin/category"})
    public String list(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categories", service.getAll());
        return "shop/admin-category-list";
    }

    @GetMapping(value = "/admin/category/{id}")
    public String details(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("category", service.getById(id));
        return "shop/admin-category-details";
    }

    @GetMapping(value = "/admin/category-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", new CategoryDto());
        model.addAttribute("categories", service.getByParentIsNull());
        return "shop/admin-category-form";
    }

    @GetMapping(value = "/admin/category/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", service.getById(id));
        model.addAttribute("categories", service.getByParentIsNullAndIdNot(id));
        return "shop/admin-category-form";
    }

    @PostMapping("/admin/category")
    public String createOrUpdate(CategoryForm category) {
        service.createOrUpdate(category);
        return "redirect:/admin/category";
    }

    @GetMapping(value = "/admin/category/{id}/delete")
    public String deleteGet(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/category";
    }

    @DeleteMapping(value = "/admin/category/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/category";
    }

    private Model setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
        return model;
    }

}
