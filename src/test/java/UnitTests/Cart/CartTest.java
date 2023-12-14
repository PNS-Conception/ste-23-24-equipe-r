package UnitTests.Cart;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testEmptyCart() {
        // Given
        cart.addMenu(createMockMenu("Burger", 10.0));
        cart.addMenu(createMockMenu("Pizza", 12.0));

        // When
        cart.emptyCart();

        // Then
        assertTrue(cart.getMenuMap().isEmpty());
    }

    @Test
    void testAddMenu() {
        // Given
        Menu burger = createMockMenu("Burger", 10.0);

        // When
        cart.addMenu(burger);

        // Then
        assertEquals(1, cart.getMenuMap().size());
        assertTrue(cart.getMenuMap().containsKey(burger));
        assertEquals(1, cart.getMenuMap().get(burger));
    }

    @Test
    void testGetSize() {
        // Given
        cart.addMenu(createMockMenu("Burger", 10.0));
        cart.addMenu(createMockMenu("Pizza", 12.0));

        // When
        int size = cart.getSize();

        // Then
        assertEquals(2, size);
    }

    @Test
    void testSetRestaurant() {
        // Given
        Restaurant restaurant = new Restaurant("FastFood");

        // When
        cart.setRestaurant(restaurant);

        // Then
        assertEquals(restaurant, cart.getRestaurant());
    }

    // Helper method to create a mock Menu
    private Menu createMockMenu(String name, double price) {
        return new Menu(name, price);
    }
}
