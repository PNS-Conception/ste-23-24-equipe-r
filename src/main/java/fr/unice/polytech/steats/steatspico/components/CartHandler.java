package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.cart.Cart;
import fr.unice.polytech.steats.steatspico.interfaces.cart.CartModifier;
import fr.unice.polytech.steats.steatspico.interfaces.cart.CartTotalCalculator;
import fr.unice.polytech.steats.steatspico.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.util.Map;

public class CartHandler implements CartModifier, CartTotalCalculator {
    private final Cart cart;

    public CartHandler(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addItem(Restaurant restaurant, Menu menu, int quantity) {
        cart.addMenu(restaurant, menu, quantity);
    }

    @Override
    public void removeItem(Restaurant restaurant, Menu menu, int quantityToRemove) throws MenuRemovalFromCartException {
        Map<Menu, Integer> menus = cart.getRestaurantMenusMap().get(restaurant);
        if (menus != null && menus.containsKey(menu)) {
            int existingQuantity = menus.get(menu);

            if (existingQuantity > quantityToRemove) {
                menus.put(menu, existingQuantity - quantityToRemove);
            } else if (existingQuantity == quantityToRemove) {
                menus.remove(menu);
            } else {
                throw new MenuRemovalFromCartException();
            }
        }
    }

    @Override
    public double getPriceForUser(CampusUser campusUser) {
        double total = 0;
        for (Map.Entry<Restaurant, Map<Menu, Integer>> restaurantEntry : cart.getRestaurantMenusMap().entrySet()) {
            for (Map.Entry<Menu, Integer> entry : restaurantEntry.getValue().entrySet()) {
                Menu menu = entry.getKey();
                int quantity = entry.getValue();
                if (menu.getCampusUserStatusPrice().containsKey(campusUser.getStatus())) {
                    double priceForUser = menu.getCampusUserStatusPrice().get(campusUser.getStatus());
                    total += priceForUser * quantity;
                } else {
                    total += menu.getBasePrice() * quantity;
                }
            }
        }
        return total;
    }
}
