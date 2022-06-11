package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.core.util.ControllerTools;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.ContactType;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AccountWebController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public AccountWebController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping(value = "/account")
    public String form(Model model, HttpServletRequest request, Authentication authentication) {
        generateMenu(model, request);
        model.addAttribute("userForm", userService.getFormByEmail(authentication.getName()));
        return "shop/account";
    }

    @PostMapping(value = "/account")
    public String update(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, Model model,
                         HttpServletRequest request, Authentication authentication
    ) {
        userForm.setEmail(authentication.getName());
        if (!result.hasErrors() && !userForm.getUuid().isEmpty()) {
            userService.createOrUpdate(userForm, encoder);
            model.addAttribute("userForm", userService.getFormByEmail(authentication.getName()));
        }
        generateMenu(model, request);
        return "shop/account";
    }

    private void generateMenu(
            Model model,
            HttpServletRequest request
    ) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
    }

}
