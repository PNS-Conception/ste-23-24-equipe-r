package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.order.Cart;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.ArrayList;

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
        return this.cart;
    }

    public void addMenuToCart(Menu menu) {
        cart.addToCart(menu);
    }

    public void removeFromCart(Menu menu) {
        cart.removeFromCart(menu);
    }
}
