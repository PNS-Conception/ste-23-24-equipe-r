package fr.unice.polytech.steats.unit.cart;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.users.CampusUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CartHandlerTest {
    private CartHandler cartHandler;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cartHandler = new CartHandler(cart);
    }

    @Test
    void testAddItem() {
        // Given
        Menu burger = new Menu("Burger", 10.0);

        // When
        cartHandler.addItem(burger, 2);

        // Then
        assertEquals(2, cart.getMenuMap().get(burger));
    }

    @Test
    void testRemoveItem() {
        // Given
        Menu pizza = new Menu("Pizza", 12.0);
        cartHandler.addItem(pizza, 3);

        // When
        assertDoesNotThrow(() -> cartHandler.removeItem(pizza, 2));

        // Then
        assertEquals(1, cart.getMenuMap().get(pizza));
    }

    @Test
    void testRemoveItemException() {
        // Given
        Menu pasta = new Menu("Pasta", 15.0);
        cartHandler.addItem(pasta, 3);

        // When/Then
        assertThrows(MenuRemovalFromCartException.class, () -> cartHandler.removeItem(pasta, 5));
    }

    @Test
    void testGetPriceForUser() {
        // Given
        Menu burger = new Menu("Burger", 10.0);
        Menu pizza = new Menu("Pizza", 12.0);
        cartHandler.addItem(burger, 2);
        cartHandler.addItem(pizza, 3);

        CampusUser campusUser = new CampusUser("Student");

        // When
        double totalPrice = cartHandler.getPriceForUser(campusUser);

        // Then
        assertEquals(2 * burger.getBasePrice() + 3 * pizza.getBasePrice(), totalPrice);
    }

}
