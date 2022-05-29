package com.lagu.shop.module.product;

import com.lagu.shop.module.product.dto.ProductDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
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

}
