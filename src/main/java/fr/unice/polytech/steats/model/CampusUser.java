package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.List;

public class CampusUser extends User {
    private Cart cart;

    public CampusUser() {
        super();
        this.cart = new Cart(new ArrayList<>());
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart(new ArrayList<>());
    }

    public CampusUser(String userID, String name) {
        super(userID, name);
        this.cart = new Cart(new ArrayList<>());
    }

    public Cart getCart() {
        return cart;
    }

    public void addMenuToCart(Menu menu) {
        cart.addToCart(menu);
    }

    public void removeFromCart(Menu menu) {
        cart.removeFromCart(menu);
    }
}
