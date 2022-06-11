package com.lagu.shop.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.shop.core.pagination.MenuNavigator;
import com.lagu.shop.module.product.ProductWebController;
import com.lagu.shop.module.product.dto.Forecast;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.service.ForecastService;
import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.entity.ContactType;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ControllerTools {

    public static ModelAndView redirectToShop(HttpSession httpSession) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("/shop");
        Object oPage = httpSession.getAttribute("page");
        Object oSize = httpSession.getAttribute("size");
        int page = Integer.parseInt((oPage == null) ? ProductWebController.DEFAULT_PAGE : oPage.toString());
        int size = Integer.parseInt((oSize == null) ? ProductWebController.DEFAULT_SIZE : oSize.toString());
        uriComponentsBuilder.queryParam("page", page);
        uriComponentsBuilder.queryParam("size", size);
        Object oCategory = httpSession.getAttribute("category");
        if (oCategory != null) {
            String category = oCategory.toString();
            uriComponentsBuilder.queryParam("category", category);
        }
        return new ModelAndView("redirect:" + uriComponentsBuilder.toUriString());
    }

    public static ModelAndView redirectToShop() {
        return new ModelAndView("redirect:/shop");
    }

    public static ModelAndView addOrRemove(
            String uuid,
            Authentication authentication,
            BiConsumer<String, String> action,
            HttpSession httpSession
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            action.accept(uuid, authentication.getName());
            return redirectToShop(httpSession);
        }
        return redirectToShop();
    }

    public static List<ProductDto> setProductAsAdded(
            List<ProductDto> products,
            Authentication authentication,
            Function<String, Set<String>> service,
            BiConsumer<ProductDto, Boolean> setValue
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            Set<String> items = service.apply(authentication.getName());
            for (ProductDto product : products) {
                if (items.contains(product.getUuid())) {
                    setValue.accept(product, true);
                }
            }
        }
        return products;
    }

    public static boolean isLogged(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    public static void setCommonModelSettings(
            Model model,
            Authentication authentication,
            HttpSession httpSession,
            UserService userService,
            ObjectMapper objectMapper,
            ForecastService forecastService,
            boolean isLogged,
            String uri
    ) {
        if (isLogged) {
            Double temperature = null;
            Integer pressure = null;
            Integer humidity = null;
            Forecast forecast = new Forecast();
            try {
                temperature = Double.parseDouble(httpSession.getAttribute("temperature").toString());
                pressure = Integer.parseInt(httpSession.getAttribute("pressure").toString());
                humidity = Integer.parseInt(httpSession.getAttribute("humidity").toString());
                forecast = new Forecast(temperature, pressure, humidity);
            } catch (Exception e) {
                // ignore
            }
            if (temperature == null || pressure == null || humidity == null) {
                UserDto user = userService.getDtoByEmail(authentication.getName());
                forecast = forecastService.getForecast(objectMapper, user.getLatitude(), user.getLongitude());
            }
            model.addAttribute("forecast", forecast);
        }
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
    }

    public static <R> R setEnumValue(R[] enumArray, R enumDefault, String enumText) {
        for (R enumItem : enumArray) {
            if (enumItem.toString().equals(enumText)) {
                return enumItem;
            }
        }
        return enumDefault;
    }

    public static <R> List<String> getEnumAsStringList(R[] enumArray) {
        List<String> listSting = new ArrayList<>();
        for (R enumItem : enumArray) {
            listSting.add(enumItem.toString());
        }
        return listSting;
    }

}
