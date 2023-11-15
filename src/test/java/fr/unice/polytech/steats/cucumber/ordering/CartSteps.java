package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartService;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartSteps {
    CampusUserRegistry campusUserRegistry;
    Cart cart;
    CampusUser campusUser;
    CartService cartService;
    Restaurant restaurant;
    TimeSlot timeSlot;
    RestaurantRegistry restaurantRegistry;
    public CartSteps(FacadeContainer container){
        this.campusUserRegistry = container.campusUserRegistry;
        this.restaurantRegistry = container.restaurantRegistry;
    }
    @When("{string} checks his cart's contents")
    public void checksCartContents(String customerName) {
        campusUser = campusUserRegistry.findByName(customerName).get();
        cart = campusUser.getCart();
    }
    @Then("there is {int} menus in his cart")
    public void verifyNumberOfMenusInCart(int numberOfMenus) {
        assertEquals(numberOfMenus, cart.getSize());
    }
    @When("{string} chooses {int} x {string}")
    public void addMenusToCart(String customerName, int quantity, String menuName) {
        campusUser = campusUserRegistry.findByName(customerName).get();
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartService = new CartService(cart);
        cartService.addItem(menu, quantity);
    }

    @And("{string} removes {int} x {string}")
    public void removeFromCart(String customerName, int quantity, String menuName)
            throws MenuRemovalFromCartException {
        campusUser = campusUserRegistry.findByName(customerName).get();
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartService = new CartService(cart);
        cartService.removeItem(menu, quantity);
    }
    @And("the cart contains the menus : {int} x {string}")
    public void verifyMultipleMenusInCart(int quantity, String menuName) {
        Menu menu = restaurant.getMenufromName(menuName);
        assertTrue(cart.getMenuMap().containsKey(menu));
        assertEquals((int) cart.getMenuMap().get(menu), quantity);
    }

    @Then("the price of {string}'s cart is {double}")
    public void verifyCartPrice(String customerName, double cartPrice) {
        campusUser = campusUserRegistry.findByName(customerName).get();
        assertEquals(cartService.getPriceForUser(campusUser), cartPrice, 0.01);
    }
    @Then("timeslot {string} should have capacity {int}")
    public void timeslotShouldHaveCapacity(String timeslotString, int capacity) {
        LocalTime openingTime = LocalTime.parse(timeslotString);
        timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        assertEquals(capacity, timeSlot.getCapacity());
    }

    @When("{string} chooses the restaurant {string}")
    public void choosesTheRestaurant(String username, String restaurantName) {
        campusUser = campusUserRegistry.findByName(username).get();
        cart = campusUser.getCart();
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        cart.setRestaurant(restaurant);
    }
}
