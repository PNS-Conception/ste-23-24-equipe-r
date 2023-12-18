package fr.unice.polytech.steats.unit.cart;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CartHandlerTest {
    private CartHandler cartHandler;
    private Cart cart;
    private Restaurant mockRestaurant;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        mockRestaurant = mock(Restaurant.class); // Mocking Restaurant
        cartHandler = new CartHandler(cart);
    }

    @Test
    void testAddItem() {
        // Given
        Menu burger = new Menu("Burger", 10.0);

        // When
        cartHandler.addItem(mockRestaurant, burger, 2); // Using mocked Restaurant

        // Then
        Map<Menu, Integer> menusFromMockRestaurant = cart.getRestaurantMenusMap().get(mockRestaurant);
        assertNotNull(menusFromMockRestaurant);
        assertEquals(2, menusFromMockRestaurant.get(burger));
    }

    @Test
    void testRemoveItem() throws MenuRemovalFromCartException {
        // Given
        Menu pizza = new Menu("Pizza", 12.0);
        cartHandler.addItem(mockRestaurant, pizza, 3);

        // When
        cartHandler.removeItem(mockRestaurant, pizza, 2);

        // Then
        Map<Menu, Integer> menusFromMockRestaurant = cart.getRestaurantMenusMap().get(mockRestaurant);
        assertNotNull(menusFromMockRestaurant);
        assertEquals(1, menusFromMockRestaurant.get(pizza));
    }

    @Test
    void testRemoveItemException() {
        // Given
        Menu pasta = new Menu("Pasta", 15.0);
        cartHandler.addItem(mockRestaurant, pasta, 3);

        // When/Then
        assertThrows(MenuRemovalFromCartException.class, () -> cartHandler.removeItem(mockRestaurant, pasta, 5));
    }

    @Test
    void testGetPriceForUser() {
        // Given
        Menu burger = new Menu("Burger", 10.0);
        Menu pizza = new Menu("Pizza", 12.0);
        cartHandler.addItem(mockRestaurant, burger, 2);
        cartHandler.addItem(mockRestaurant, pizza, 3);

        CampusUser campusUser = new CampusUser("Student");

        // When
        double totalPrice = cartHandler.getPriceForUser(campusUser);

        // Then
        assertEquals(2 * burger.getBasePrice() + 3 * pizza.getBasePrice(), totalPrice);
    }
}
