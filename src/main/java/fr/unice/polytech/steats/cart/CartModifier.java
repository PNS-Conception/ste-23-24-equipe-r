package fr.unice.polytech.steats.cart;

import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;

public interface CartModifier {
    void addItem(Menu menu, int quantity);

    void removeItem(Menu menu, int quantityToRemove) throws MenuRemovalFromCartException;
}
