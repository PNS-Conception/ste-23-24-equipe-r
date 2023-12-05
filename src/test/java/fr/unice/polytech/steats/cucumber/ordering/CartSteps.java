package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.cart.CartModifier;
import fr.unice.polytech.steats.cart.CartTotalCalculator;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.restaurant.*;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserFinder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartSteps {
    final CampusUserFinder campusUserFinder;
    Cart cart;
    CampusUser campusUser;
    CartModifier cartModifier;
    CartTotalCalculator cartTotalCalculator;
    Restaurant restaurant;
    final RestaurantLocator restaurantLocator;
    public CartSteps(FacadeContainer container){
        this.campusUserFinder = container.campusUserRegistry;
        this.restaurantLocator = container.restaurantLocator;
    }
    @When("{string} checks his cart's contents")
    public void checksCartContents(String customerName) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(customerName)
                .orElseThrow(() -> new NoSuchElementException("Element not found"));

        cart = campusUser.getCart();
    }
    @Then("there is {int} menus in his cart")
    public void verifyNumberOfMenusInCart(int numberOfMenus) {
        assertEquals(numberOfMenus, cart.getSize());
    }
    @When("{string} chooses {int} x {string}")
    public void addMenusToCart(String customerName, int quantity, String menuName) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartModifier = new CartHandler(cart);
        cartModifier.addItem(menu, quantity);
    }

    @And("{string} removes {int} x {string}")
    public void removeFromCart(String customerName, int quantity, String menuName)
            throws MenuRemovalFromCartException, NoSuchElementException {
        campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartModifier = new CartHandler(cart);
        cartModifier.removeItem(menu, quantity);
    }
    @And("the cart contains the menus : {int} x {string}")
    public void verifyMultipleMenusInCart(int quantity, String menuName) {
        Menu menu = restaurant.getMenufromName(menuName);
        assertTrue(cart.getMenuMap().containsKey(menu));
        assertEquals((int) cart.getMenuMap().get(menu), quantity);
    }

    @Then("the price of {string}'s cart is {double}")
    public void verifyCartPrice(String customerName, double cartPrice) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cartTotalCalculator = new CartHandler(cart);
        assertEquals(cartTotalCalculator.getPriceForUser(campusUser), cartPrice, 0.01);
    }

    @Then("timeslot {string} should have capacity {int}")
    public void timeslotShouldHaveCapacity(String dateTimeString, int expectedCapacity) throws NoSuchElementException {
        LocalDateTime timeslotDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(timeslotDateTime);
        TimeSlot timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(timeslotDateTime).orElseThrow(() -> new NoSuchElementException("Element not found"));
        assertEquals(expectedCapacity, timeSlot.getCapacity());
    }

    @When("{string} chooses the restaurant {string}")
    public void choosesTheRestaurant(String username, String restaurantName) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(username).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart.setRestaurant(restaurant);
    }

}
