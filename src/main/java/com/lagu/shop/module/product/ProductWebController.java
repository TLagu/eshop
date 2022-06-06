package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.*;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.dto.PageSetup;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductWebController {
    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "6";

    private final ProductService service;
    private final HttpSession httpSession;
    private final CartWebController cartWebController;
    private final CategoryRepository categoryRepository;
    private final CompareWebController compareWebController;
    private final WishlistWebController wishlistWebController;

    public ProductWebController(
            ProductService service,
            HttpSession httpSession,
            CartWebController cartWebController,
            CategoryRepository categoryRepository,
            CompareWebController compareWebController,
            WishlistWebController wishlistWebController
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.cartWebController = cartWebController;
        this.categoryRepository = categoryRepository;
        this.compareWebController = compareWebController;
        this.wishlistWebController = wishlistWebController;
    }

    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        boolean isLogged = ControllerTools.isLogged(authentication);
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliders", randomForSlider);
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
        return "shop/index";
    }

    @GetMapping(value = {"/shop"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(value = "category", required = false) Long category,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        boolean isLogged = ControllerTools.isLogged(authentication);
        PageSetup pageSetup = new PageSetup(uri, isLogged);

        httpSession.setAttribute("page", String.valueOf(page));
        httpSession.setAttribute("size", String.valueOf(size));

        ListResponse<ProductDto> allPerPage;
        Map<String, String> params = new HashMap<>();

        if (category != null) {
            httpSession.setAttribute("category", category);
            allPerPage = service.getByCategories(category, page, size);
            params.put("category", String.valueOf(category));
        } else {
            allPerPage = service.getAllPerPage(page, size);
        }

        PageWrapper pageWrapper = new PageWrapper(allPerPage.getMetadata(), uri, params);
        List<ProductDto> products = allPerPage.getContent();
        if (isLogged) {
            products = cartWebController.setProductAsAdded(products, authentication);
            products = compareWebController.setProductAsAdded(products, authentication);
            products = wishlistWebController.setProductAsAdded(products, authentication);
        }
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
        model.addAttribute("pageSetup", pageSetup);
        model.addAttribute("categories", categories);
        return "shop/product";
    }

    @GetMapping(value = {"/shop/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        boolean isLogged = ControllerTools.isLogged(authentication);
        ProductDto product = service.getByUuid(uuid);
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
        model.addAttribute("productDetails", product);
        model.addAttribute("categories", categories);
        return "shop/product-details";
    }

}
