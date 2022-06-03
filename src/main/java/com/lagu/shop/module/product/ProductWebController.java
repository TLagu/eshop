package com.lagu.shop.module.product;

import com.lagu.shop.core.pagination.*;
import com.lagu.shop.module.product.dto.ProductDto;
import com.lagu.shop.module.product.dto.PageSetup;
import com.lagu.shop.module.product.entity.CategoryEntity;
import com.lagu.shop.module.product.repository.CategoryRepository;
import com.lagu.shop.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductService service;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CartWebController cartWebController;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CompareWebController compareWebController;
    @Autowired
    private WishlistWebController wishlistWebController;

    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        boolean isLogged = ControllerTools.isLogged(authentication);
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliderItems", randomForSlider);
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu(uri, isLogged));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(uri, isLogged));
        return "shop/index.html";
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
        model.addAttribute("productItems", products);
        model.addAttribute("pageItems", pageWrapper.getPageWrapper());
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu(uri, isLogged));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(uri, isLogged));
        model.addAttribute("pageSetup", pageSetup);
        model.addAttribute("categoryItems", categories);
        return "shop/product.html";
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
        ProductDto product = service.getOne(uuid);
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        model.addAttribute("bottomMenuItems", new MenuNavigator().getBottomMenu(uri, isLogged));
        model.addAttribute("middleMenuItems", new MenuNavigator().getMiddleMenu(uri, isLogged));
        model.addAttribute("productDetails", product);
        model.addAttribute("categoryItems", categories);
        return "shop/product-details.html";
    }

//
//    @GetMapping(value = "/shop/details/{uuid}")
//    public String details(@PathVariable("uuid") String uuid, Model model) {
//            model.addAttribute("products", service.getOne(uuid));
//            return "shop/details.html";
//    }
//
//    @GetMapping("/shop")
//    public ListResponse<ProductDto> getByPage(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size
//    ) {
//        return service.getAllPerPage(page, size);
//    }
//
//    @PostMapping("/shop")
//    public ProductDto create(@RequestBody ProductForm form) {
//        return service.create(form);
//    }
//
//    @PutMapping(value = "/shop/{uuid}")
//    public ProductDto update(@PathVariable String uuid,
//                              @RequestBody ProductForm form) {
//        return service.update(uuid, form);
//    }
//
//    @DeleteMapping(value = "/shop/{uuid}")
//    public void delete(@PathVariable String uuid) {
//        // TODO: IF NULL
//        // TODO: jak zwrócić KOD 202
//        service.delete(uuid);
//    }
}
