package fr.unice.polytech.steats.steatspico.interfaces.cart;

import fr.unice.polytech.steats.steatspico.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;

public interface CartModifier {
    void addItem(Restaurant restaurant, Menu menu, int quantity);
    void removeItem(Restaurant restaurant, Menu menu, int quantityToRemove) throws MenuRemovalFromCartException;
}
