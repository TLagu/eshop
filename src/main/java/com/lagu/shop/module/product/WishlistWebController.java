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

@Controller
public class WishlistWebController {

    private final WishlistService service;
    private final HttpSession httpSession;

    public WishlistWebController(WishlistService service, HttpSession httpSession) {
        this.service = service;
        this.httpSession = httpSession;
    }

    @GetMapping({"/wishlist/add/{uuid}"})
    public ModelAndView addToCart(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::add, httpSession);
    }

    @GetMapping({"/wishlist/remove/{uuid}"})
    public ModelAndView removeFromCart(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::remove, httpSession);
    }

    public List<ProductDto> setProductAsAdded(List<ProductDto> products, Authentication authentication) {
        return ControllerTools.setProductAsAdded(products, authentication, service::getProductListByUser, ProductDto::setWishlist);
    }

}
