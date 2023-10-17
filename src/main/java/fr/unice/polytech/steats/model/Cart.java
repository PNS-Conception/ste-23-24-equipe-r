package fr.unice.polytech.steats.model;


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

    // add menu and add its price
    public void addToCart(Menu menu) {
        menusList.add(menu);
        this.cartPrice+= menu.getPrice();
    }
    // remove menu and substract its price
    public void removeFromCart(Menu menu) {
        if (this.menusList.remove(menu)) {
            this.cartPrice -= menu.getPrice();
        }
    }
}
