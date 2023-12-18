package fr.unice.polytech.steats.cucumber.cart;

import fr.unice.polytech.steats.cucumber.picosetup.FacadeContainer;
import fr.unice.polytech.steats.steatspico.entities.cart.Cart;
import fr.unice.polytech.steats.steatspico.components.CartHandler;
import fr.unice.polytech.steats.steatspico.interfaces.cart.CartModifier;
import fr.unice.polytech.steats.steatspico.interfaces.cart.CartTotalCalculator;
import fr.unice.polytech.steats.steatspico.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.steatspico.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.NonExistentMenuException;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.steatspico.entities.restaurant.TimeSlot;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.interfaces.users.CampusUserFinder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
    public void addMenusToCart(String customerName, int quantity, String menuName) throws NoSuchElementException, NonExistentMenuException {
        campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartModifier = new CartHandler(cart);
        cartModifier.addItem(restaurant, menu, quantity);
    }

    @And("{string} removes {int} x {string}")
    public void removeFromCart(String customerName, int quantity, String menuName)
            throws MenuRemovalFromCartException, NoSuchElementException, NonExistentMenuException {
        campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        cartModifier = new CartHandler(cart);
        cartModifier.removeItem(restaurant, menu, quantity);
    }
    @And("the cart contains the menus : {int} x {string}")
    public void verifyMultipleMenusInCart(int quantity, String menuName) throws NonExistentMenuException {
        Menu menu = restaurant.getMenufromName(menuName);
        Map<Menu, Integer> menusFromRestaurant = cart.getRestaurantMenusMap().get(restaurant);
        assertTrue("Menu should be present in the cart", menusFromRestaurant != null && menusFromRestaurant.containsKey(menu));
        assertEquals("Menu quantity should match", quantity, (int) menusFromRestaurant.get(menu));
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
    }

    @When("{string} chooses the {string} at {string} for {int} participants")
    public void choosesTheAt(String userName, String buffetName, String restaurantName, int number) throws NoSuchElementException, NonExistentMenuException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        campusUser = campusUserFinder.findByName(userName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(buffetName);
        cartModifier = new CartHandler(cart);
        cartModifier.addItem(restaurant, menu, number);
    }

    @Then("the total price for the {string} at {string} should be {double}")
    public void theTotalPriceForTheAtShouldBe(String arg0, String arg1, int arg2, int arg3) {
    }
}
