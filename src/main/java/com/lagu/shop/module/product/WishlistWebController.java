package com.lagu.shop.module.product;

import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class WishlistWebController {

    @Autowired
    private WishlistService service;
    @Autowired
    private HttpSession httpSession;

    @GetMapping({"/wishlist/add/{uuid}"})
    public ModelAndView addToCart(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            service.add(uuid, authentication.getName());
            return ControllerTools.redirectToShop(httpSession);
        }
        return new ModelAndView("redirect:/shop");
    }

    @GetMapping({"/wishlist/remove/{uuid}"})
    public ModelAndView removeFromCart(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            service.remove(uuid, authentication.getName());
            return ControllerTools.redirectToShop(httpSession);
        }
        return new ModelAndView("redirect:/shop");
    }

    public List<ProductDto> setProductAsAdded(List<ProductDto> products, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Set<String> items = service.getProductListByUser(authentication.getName());
            for (ProductDto product : products) {
                if (items.contains(product.getUuid())) {
                    product.setWishlist(true);
                }
            }
        }
        return products;
    }

}
