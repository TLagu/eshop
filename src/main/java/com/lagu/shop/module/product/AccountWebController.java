package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccountWebController {

    private final OrderService service;

    public AccountWebController(OrderService service) {
        this.service = service;
    }

    @GetMapping({"/order"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        List<OrderDto> orders = service.getUserOrders(authentication);
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("orders", orders);
        return "shop/orders";
    }

    @GetMapping({"/order/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            HttpServletRequest request,
            Model model,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        OrderDto order = service.getOrderByUuid(authentication, uuid);
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("order", order);
        return "shop/order";
    }

}
