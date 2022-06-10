package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.dto.CategoryForm;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.service.CategoryService;
import com.lagu.shop.module.product.service.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AdminCategoryWebController {

    private final CategoryService service;
    private final TemplateService templateService;

    public AdminCategoryWebController(CategoryService service, TemplateService templateService) {
        this.service = service;
        this.templateService = templateService;
    }

    @GetMapping({"/admin/category"})
    public String list(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categories", service.getAll());
        return "shop/admin-category-list";
    }

    @GetMapping(value = "/admin/category/{id}")
    public String details(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("category", service.getDtoById(cid));
        return "shop/admin-category-details";
    }

    @GetMapping(value = "/admin/category-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("categories", service.getByParentIsNull());
        return "shop/admin-category-form";
    }

    @GetMapping(value = "/admin/category/{id}/edit")
    public String edit(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", service.getFormById(cid));
        model.addAttribute("categories", service.getByParentIsNullAndIdNot(cid));
        return "shop/admin-category-form";
    }

    @PostMapping("/admin/category")
    public String createOrUpdate(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
                                 BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            setCommonAttributes(request, model);
            model.addAttribute("categories", service.getByParentIsNull());
            return "shop/admin-category-form";
        }
        CategoryEntity categoryEntity = service.createOrUpdate(categoryForm);
        return "redirect:/admin/category/" + categoryEntity.getId() + "/edit";
    }

    @GetMapping(value = "/admin/category/{id}/delete")
    public String deleteGet(@PathVariable("id") Long cid) {
        service.delete(cid);
        return "redirect:/admin/category";
    }

    @DeleteMapping(value = "/admin/category/{id}/delete")
    public String deletePost(@PathVariable("id") Long cid) {
        service.delete(cid);
        return "redirect:/admin/category";
    }

    @GetMapping(value = "/admin/category/{id}/template/add")
    public String addTemplate(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        templateService.addTemplate(cid);
        model.addAttribute("categoryForm", service.getDtoById(cid));
        model.addAttribute("categories", service.getByParentIsNullAndIdNot(cid));
        return "redirect:/admin/category/" + cid + "/edit";
    }

    @GetMapping(value = "/admin/category/{cid}/template/{tid}/delete")
    public String deleteTemplateGet(@PathVariable Long cid, @PathVariable Long tid) {
        templateService.delete(cid, tid);
        return "redirect:/admin/category/" + cid + "/edit";
    }

    @PostMapping(value = "/admin/category/{cid}/template/{tid}/delete")
    public String deleteTemplatePost(@PathVariable Long cid, @PathVariable Long tid) {
        templateService.delete(cid, tid);
        return "redirect:/admin/category/" + cid + "/edit";
    }

    private void setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
    }

}
