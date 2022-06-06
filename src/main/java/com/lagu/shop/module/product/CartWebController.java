package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.dto.CartForm;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.service.CartService;
import com.lagu.shop.module.product.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartWebController {

    private final CartService service;
    private final OrderService orderService;
    private final HttpSession httpSession;

    public CartWebController(CartService service, OrderService orderService, HttpSession httpSession) {
        this.service = service;
        this.orderService = orderService;
        this.httpSession = httpSession;
    }

    @GetMapping({"/cart/add/{uuid}"})
    public ModelAndView add(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::add, httpSession);
    }

    @GetMapping({"/cart/remove/{uuid}"})
    public ModelAndView remove(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::remove, httpSession);
    }


    @GetMapping({"/cart"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        return showList(model, request, authentication);
    }

    @GetMapping({"/cart/remove-item/{uuid}"})
    public String removeItem(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.remove(uuid, authentication.getName());
        return showList(model, request, authentication);
    }


    @GetMapping({"/cart/remove-amount/{uuid}"})
    public String removeAmount(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.removeAmount(uuid, authentication.getName());
        return showList(model, request, authentication);
    }

    @GetMapping({"/cart/add-amount/{uuid}"})
    public String addAmount(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.addAmount(uuid, authentication.getName());
        return showList(model, request, authentication);
    }

    @PostMapping({"/cart"})
    public String makeOrder(
            Model model,
            HttpServletRequest request,
            Authentication authentication,
            OrderDto order
    ) {
        if (orderService.saveOrder(order, authentication)) {
            model.addAttribute("comment", "Zamówienie zrealizowane.");
        } else {
            model.addAttribute("comment", "Błąd realizacji zamówienia");
        }
        return showList(model, request, authentication);
    }


    public List<ProductDto> setProductAsAdded(List<ProductDto> products, Authentication authentication) {
        return ControllerTools.setProductAsAdded(products, authentication, service::getProductUuidListByUser, ProductDto::setCart);
    }

    private String showList(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        List<CartForm> carts = service.getProductListByUser(authentication.getName());
        OrderDto order = orderService.getInitialOrder(authentication);
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("carts", carts);
        model.addAttribute("order", order);
        return "shop/cart";
    }

}
