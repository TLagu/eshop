package com.lagu.shop.core.pagination;

import java.util.List;

public class MenuNavigator {

    private final List<Menu> bottomMenu = List.of(
            new Menu("", "Strona główna", "/"),
            new Menu("", "Sklep", "/shop"),
            new Menu("", "Kontakt", "/contact")
    );

    private final List<Menu> middleMenu = List.of(
            new Menu("a fa-userEntity", "Konto", "/account"),
            new Menu("fa fa-star", "Obserwowane", "/wish-list"),
            new Menu("fa fa-crosshairs", "Porównanie", "/checkout"),
            new Menu("fa fa-shopping-cart", "Koszyk", "/cart"),
            new Menu("fa fa-lock", "Logowanie", "/logged/login")
    );

    public List<Menu> getBottomMenu(String url, String prefix) {
        for (Menu menuItem : bottomMenu) {
            menuItem.setUrl(prefix + menuItem.getUrl());
            if (menuItem.getUrl().equals(url)) {
                menuItem.setClassName("active");
            }
        }
        return bottomMenu;
    }

    public List<Menu> getMiddleMenu(String prefix) {
        for (Menu menuItem : middleMenu) {
            menuItem.setUrl(prefix + menuItem.getUrl());
        }
        return middleMenu;
    }

}
