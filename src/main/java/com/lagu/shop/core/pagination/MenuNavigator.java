package com.lagu.shop.core.pagination;

import java.util.List;

public class MenuNavigator {

    private final List<Menu> bottomMenu = List.of(
            new Menu("", "", "Strona główna", "/home"),
            new Menu("", "", "Sklep", "/shop"),
            new Menu("", "", "Kontakt", "/contact")
    );

    private final List<Menu> loggedBottomMenu = List.of(
            new Menu("", "", "Strona główna", "/logged/home"),
            new Menu("", "", "Sklep", "/logged/shop"),
            new Menu("", "", "Kontakt", "/logged/contact")
    );

    private final List<Menu> middleMenu = List.of(
            new Menu("", "fa fa-lock", "Logowanie", "/logged/login")
    );

    private final List<Menu> loggedMiddleMenu = List.of(
            new Menu("", "a fa-userEntity", "Konto", "/logged/account"),
            new Menu("", "fa fa-star", "Obserwowane", "/logged/wish-list"),
            new Menu("", "fa fa-crosshairs", "Porównanie", "/logged/checkout"),
            new Menu("", "fa fa-shopping-cart", "Koszyk", "/logged/cart"),
            new Menu("", "fa fa-lock", "Wylogowanie", "/logout")
    );

    public List<Menu> getBottomMenu(String url, boolean isLogged) {
        List<Menu> menu = (isLogged) ? loggedBottomMenu : bottomMenu;
        for (Menu menuItem : menu) {
            if (menuItem.getUrl().equals(url)) {
                menuItem.setClassState("active");
            }
        }
        return menu;
    }

    public List<Menu> getMiddleMenu(String url, boolean isLogged) {
        List<Menu> menu = (isLogged) ? loggedMiddleMenu : middleMenu;
        for (Menu menuItem : menu) {
            if (menuItem.getUrl().equals(url)) {
                menuItem.setClassState("active");
            }
        }
        return menu;
    }

}
