package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.service.OrderDetailsService;
import com.lagu.shop.module.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccountWebController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping({"/order"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        List<OrderDto> orders = service.getUserOrders(authentication);
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu(uri, true));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(uri, true));
        model.addAttribute("orderItems", orders);
        return "shop/orders.html";
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
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu(uri, true));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(uri, true));
        model.addAttribute("order", order);
        return "shop/order.html";
    }

}
