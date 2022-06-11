package com.lagu.shop.module.user;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.core.pagination.PageWrapper;
import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.UserRole;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminUserWebController {

    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "25";

    private final UserService service;
    private final BCryptPasswordEncoder encoder;

    public AdminUserWebController(UserService service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
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
        model.addAttribute("users", allPerPage.getContent());
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        return "shop/admin-user-list";
    }

    @GetMapping("/admin/user/{uuid}")
    public String details(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("user", service.getDtoByUuid(uuid));
        return "shop/admin-user-details";
    }

    @GetMapping("/admin/user-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("roles", getRoles(UserRole.values()));
        return "shop/admin-user-form";
    }

    @GetMapping("/admin/user/{uuid}/edit")
    public String edit(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("userForm", service.getFormByUuid(uuid));
        model.addAttribute("roles", getRoles(UserRole.values()));
        return "shop/admin-user-form";
    }

    @PostMapping("/admin/user")
    public String createOrUpdate(@Valid @ModelAttribute("userForm") UserForm userForm,
                                 BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            setCommonAttributes(request, model);
            model.addAttribute("roles", getRoles(UserRole.values()));
            return "shop/admin-user-form";
        }
        service.createOrUpdate(userForm, encoder);
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

    private void setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
    }

    private List<String> getRoles(UserRole[] roles) {
        return Arrays.stream(roles).map(Enum::toString).collect(Collectors.toList());
    }

}
