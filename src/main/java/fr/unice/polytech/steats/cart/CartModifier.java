package fr.unice.polytech.steats.cart;

import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

public interface CartModifier {
    void addItem(Restaurant restaurant, Menu menu, int quantity);
    void removeItem(Restaurant restaurant, Menu menu, int quantityToRemove) throws MenuRemovalFromCartException;
}
