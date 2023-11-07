package fr.unice.polytech.steats.cart;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;

public class CartService {
    private Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void addItem(Menu menu, int quantity){
        int existingQuantity = cart.getMenuMap().getOrDefault(menu, 0);
        cart.getMenuMap().put(menu, existingQuantity + quantity);
    }

    public void removeItem(Menu menu, int quantityToRemove) throws MenuRemovalFromCartException {
        if (cart.getMenuMap().containsKey(menu)) {
            int existingQuantity = cart.getMenuMap().get(menu);

            if (existingQuantity > quantityToRemove) {
                cart.getMenuMap().put(menu, existingQuantity - quantityToRemove);
            } else if (existingQuantity == quantityToRemove) {
                cart.getMenuMap().remove(menu);
            } else {
                throw new MenuRemovalFromCartException();
            }
        }
    }
}
