package fr.unice.polytech.steats.unit.cart;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Restaurant mockRestaurant;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        mockRestaurant = new Restaurant("Mock Restaurant"); // Mock or Dummy Restaurant
    }

    @Test
    void testEmptyCart() {
        // Given
        cart.addMenu(mockRestaurant, new Menu("Burger", 10.0), 1);
        cart.addMenu(mockRestaurant, new Menu("Pizza", 12.0), 1);

        // When
        cart.emptyCart();

        // Then
        assertTrue(cart.getRestaurantMenusMap().isEmpty());
    }

    @Test
    void testAddMenu() {
        // Given
        Menu burger = new Menu("Burger", 10.0);

        // When
        cart.addMenu(mockRestaurant, burger, 1);

        // Then
        Map<Menu, Integer> menusFromRestaurant = cart.getRestaurantMenusMap().get(mockRestaurant);
        assertNotNull(menusFromRestaurant);
        assertTrue(menusFromRestaurant.containsKey(burger));
        assertEquals(1, menusFromRestaurant.get(burger));
    }

    @Test
    void testGetSize() {
        // Given
        cart.addMenu(mockRestaurant, new Menu("Burger", 10.0), 1);
        cart.addMenu(mockRestaurant, new Menu("Pizza", 12.0), 1);

        // When
        int size = cart.getSize();

        // Then
        assertEquals(2, size);
    }

}
