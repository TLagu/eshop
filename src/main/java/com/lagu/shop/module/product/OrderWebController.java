package com.lagu.shop.module.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.shop.core.util.ControllerTools;
import com.lagu.shop.module.product.dto.OrderDto;
import com.lagu.shop.module.product.service.ForecastService;
import com.lagu.shop.module.product.service.OrderService;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderWebController {

    private final OrderService service;
    private final HttpSession httpSession;
    private final UserService userService;
    private final ForecastService forecastService;
    private final ObjectMapper objectMapper;

    String uri = "/order";
    boolean isLogged = true;

    public OrderWebController(
            OrderService service,
            HttpSession httpSession,
            UserService userService,
            ForecastService forecastService,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.userService = userService;
        this.forecastService = forecastService;
        this.objectMapper = objectMapper;
    }

    @GetMapping({"/order"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        uri = request.getRequestURI();
        List<OrderDto> orders = service.getUserOrders(authentication);
        setCommonModelSettings(model, authentication);
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
        uri = request.getRequestURI();
        OrderDto order = service.getOrderByUuid(authentication, uuid);
        setCommonModelSettings(model, authentication);
        model.addAttribute("order", order);
        return "shop/order";
    }

    private void setCommonModelSettings(Model model, Authentication authentication) {
        ControllerTools.setCommonModelSettings(model, authentication, httpSession, userService,
                objectMapper, forecastService, isLogged, uri);
    }

}
