package com.lagu.shop.module.user;

import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepo;

    private final BCryptPasswordEncoder encoder;

    public UserController(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String viewHomePage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/shop/register-login";
    }

    @PostMapping("/process-register")
    public String processRegister(
            Model model,
            UserEntity userEntity,
            Authentication authentication
    ) {
        UserEntity user = userRepo.findByEmail(userEntity.getEmail());
        String comment;
        if (authentication != null && authentication.isAuthenticated()) {
            comment = "Jesteś zalogowany, nie możesz się zarejestrować";
        } else if (user == null) {
            String encodedPassword = encoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
            userRepo.save(userEntity);
            comment = "Konto zostało zarejestrowane";
        } else {
            comment = "Taki adres został już zarejestrowany";
        }
        model.addAttribute("user", userEntity);
        model.addAttribute("comment", comment);
        return "shop/register-login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserEntity> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "shop/user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "shop/admin";
    }

}
