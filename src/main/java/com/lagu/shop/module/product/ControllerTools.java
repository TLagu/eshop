package com.lagu.shop.module.product;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;

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

}
