package com.lagu.shop.module.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.shop.core.pagination.*;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.dto.PageSetup;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.service.CategoryService;
import com.lagu.shop.module.product.service.ForecastService;
import com.lagu.shop.module.product.service.ProductService;
import com.lagu.shop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private final CategoryService categoryService;
    private final CompareWebController compareWebController;
    private final WishlistWebController wishlistWebController;
    private final UserService userService;
    private final ForecastService forecastService;
    private final ObjectMapper objectMapper;

    String uri = "/shop";
    boolean isLogged = false;

    public ProductWebController(
            ProductService service,
            HttpSession httpSession,
            CartWebController cartWebController,
            CategoryService categoryService,
            CompareWebController compareWebController,
            WishlistWebController wishlistWebController,
            UserService userService,
            ForecastService forecastService,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.cartWebController = cartWebController;
        this.categoryService = categoryService;
        this.compareWebController = compareWebController;
        this.wishlistWebController = wishlistWebController;
        this.userService = userService;
        this.forecastService = forecastService;
        this.objectMapper = objectMapper;
    }

    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliders", randomForSlider);
        setCommonModelSettings(model, authentication);
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
        setPageSetup(request, authentication);

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
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product";
    }

    @GetMapping(value = {"/shop/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);
        ProductDto product = service.getDtoByUuid(uuid);
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("productDetails", product);
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product-details";
    }

    @PostMapping(value = {"/shop"})
    public String search(String search, Model model, HttpServletRequest request, Authentication authentication) {
        setPageSetup(request, authentication);
        List<ProductDto> products = new ArrayList<>();
        if (search.length() > 2) {
            products = service.searchProducts(search);
        }
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product";
    }

    private void setPageSetup(HttpServletRequest request, Authentication authentication) {
        this.uri = request.getRequestURI();
        this.isLogged = ControllerTools.isLogged(authentication);
    }

    private void setCommonModelSettings(Model model, Authentication authentication) {
        ControllerTools.setCommonModelSettings(model, authentication, httpSession, userService,
                objectMapper, forecastService, isLogged, uri);
    }
}
