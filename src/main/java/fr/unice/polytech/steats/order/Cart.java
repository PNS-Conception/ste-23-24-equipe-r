package fr.unice.polytech.steats.order;


import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.List;

public class Cart {
    private List<Menu> menusList;
    private double cartPrice;
    public Cart(List<Menu> menusMap) {
        this.menusList = menusMap;
    }
    public double getCartPrice() {
        return cartPrice;
    }
    public List<Menu> getMenusList() {
        return menusList;
    }


    public void addToCart(Menu menu) {
        menusList.add(menu);
        this.cartPrice+= menu.getPrice();
    }

    public void removeFromCart(Menu menu) {
        if (this.menusList.remove(menu)) {
            this.cartPrice -= menu.getPrice();
        }
    }
}
