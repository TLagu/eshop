package com.lagu.shop.module.user;

import com.lagu.shop.core.util.ControllerTools;
import com.lagu.shop.module.user.dto.UserDto;
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

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String viewHomePage(Model model) {
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
        model.addAttribute("user", new UserForm());
        return "/shop/register-login";
    }

    @PostMapping("/process-register")
    public String processRegister(
            @Valid @ModelAttribute("user") UserForm userForm,
            BindingResult result, Model model, Authentication authentication
    ) {
        UserDto userDto = userService.getDtoByEmail(userForm.getEmail());
        String comment;
        if (result.hasErrors()) {
            comment = "Błąd wypełnienia danych";
        } else {
            if (authentication != null && authentication.isAuthenticated()) {
                comment = "Jesteś zalogowany, nie możesz się zarejestrować";
            } else if (userDto == null) {
                ContactType contact = ControllerTools.setEnumValue(ContactType.values(), ContactType.EMAIL,
                        userForm.getContact());
                userService.save(userForm, contact, encoder);
                comment = "Konto zostało zarejestrowane";
            } else {
                comment = "Taki adres został już zarejestrowany";
            }
            model.addAttribute("user", userForm);
        }
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
        model.addAttribute("comment", comment);
        return "shop/register-login";
    }

}