package com.lagu.shop.module.user;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.core.pagination.PageWrapper;
import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.UserRole;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminUserWebController {

    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "25";

    private final UserService service;

    public AdminUserWebController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/admin/user"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            Model model,
            HttpServletRequest request
    ) {
        setCommonAttributes(request, model);
        ListResponse<UserDto> allPerPage = service.getAllPerPage(page, size);
        PageWrapper pageWrapper = new PageWrapper(allPerPage.getMetadata(), request.getRequestURI(), new HashMap<>());
        List<UserDto> users = allPerPage.getContent();
        model.addAttribute("users", users);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        return "shop/admin-user-list";
    }

    @GetMapping("/admin/user/{uuid}")
    public String details(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("user", service.getByUuid(uuid));
        return "shop/admin-user-details";
    }

    @GetMapping("/admin/user-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        List<String> roles = Arrays.stream(UserRole.values()).map(Enum::toString).collect(Collectors.toList());
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("roles", roles);
        return "shop/admin-user-form";
    }

    @GetMapping("/admin/user/{uuid}/edit")
    public String edit(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        List<UserRole> roles = Arrays.stream(UserRole.values()).collect(Collectors.toList());
        model.addAttribute("userForm", service.getByUuid(uuid));
        model.addAttribute("roles", roles);
        return "shop/admin-user-form";
    }

    @PostMapping("/admin/user")
    public String createOrUpdate(UserForm user) {
        service.createOrUpdate(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{uuid}/delete")
    public String deleteGet(@PathVariable String uuid) {
        service.delete(uuid);
        return "redirect:/admin/user";
    }

    @DeleteMapping("/admin/user/{uuid}/delete")
    public String deletePost(@PathVariable String uuid) {
        service.delete(uuid);
        return "redirect:/admin/user";
    }

    private Model setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
        return model;
    }

}
