package fr.unice.polytech.steats.cucumber.ordering;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.grouporder.*;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserFinder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class GroupOrderSteps {
    GroupOrder groupOrder;
    String groupOrderCode;
    CampusUser campusUser;
    Restaurant restaurant;
    final CampusUserFinder campusUserFinder;
    final RestaurantLocator restaurantLocator;
    final GroupOrderFinder groupOrderFinder;
    final GroupOrderRegistration groupOrderRegistration;
    final SubOrderManager subOrderManager;
    DeliveryLocation deliveryLocation;

    public GroupOrderSteps(FacadeContainer container){
        campusUserFinder = container.campusUserRegistry;
        restaurantLocator = container.restaurantLocator;
        groupOrderFinder = container.groupOrderFinder;
        groupOrderRegistration = container.groupOrderRegistration;
        subOrderManager = container.subOrderManager;
    }


    @And("a group order exists with the code {string} of user {string} with restaurant {string}")
    public void aGroupOrderExistsWithTheCodeOfUser(String groupOrderString, String campusUserName, String restaurantName) throws NoSuchElementException {
        groupOrderCode = groupOrderString;
        campusUser = campusUserFinder.findByName(campusUserName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    @And("group order {string} is set with delivery time {string} and location {string}")
    public void groupOrderIsSetWithTimeslotAndLocation(String groupOrderCode, String dateTimeString, String locationString) {
        LocalDateTime deliveryDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        deliveryLocation = DeliveryLocation.getByName(locationString);
        groupOrder = groupOrderRegistration.register(campusUser, deliveryDateTime, deliveryLocation);
        groupOrder.setGroupOrderCode(groupOrderCode);
    }


    @When("{string} requests to create a group order")
    public void requestsToCreateAGroupOrder(String user) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(user).orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    @Then("a group order is created with a unique code")
    public void aGroupOrderIsCreatedWithAUniqueCode() {
        groupOrderRegistration.register(campusUser,LocalDateTime.now(),deliveryLocation);
    }

    @And("the group order is in {string} status")
    public void theGroupOrderIsInStatus(String status) {
        boolean isOpen = status.equals("open");
        assertEquals(isOpen, groupOrder.isOpen());
    }

    @When("{string} joins the group order {string}")
    public void joinsTheGroupOrder(String userName, String groupOrderCode) throws NoSuchElementException {
        groupOrder = groupOrderFinder.findByCode(groupOrderCode).orElseThrow(() -> new NoSuchElementException("Element not found"));
        campusUser = campusUserFinder.findByName(userName).orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    @And("{string} orders and pays for {int} x {string}")
    public void ordersAndPaysForX(String userName, int quantity, String menuName) throws EmptyCartException, PaymentException, InsufficientTimeSlotCapacity, NonExistentGroupOrder, ClosedGroupOrderException, DeliveryDateNotAvailable, NoSuchElementException {
        campusUser = campusUserFinder.findByName(userName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        Cart cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        CartHandler cartHandler = new CartHandler(cart);
        cartHandler.addItem(menu, quantity);
        subOrderManager.addSubOrder(groupOrderCode, restaurant, campusUser, campusUser.getCart().getMenuMap());
    }

    @And("{string}'s order should be set with delivery time {string} and location {string}")
    public void sOrderShouldBeSetWithTimeslotAndLocation(String username, String dateTimeString, String delivLocation) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(username).orElseThrow(() -> new NoSuchElementException("Element not found"));
        LocalDateTime timeslotDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DeliveryLocation deliveryLocation = DeliveryLocation.getByName(delivLocation);
        SimpleOrder order = groupOrder.getSubOrders().get(0);
        assertEquals(timeslotDateTime, order.getDeliveryTime());
        assertEquals(order.getDeliveryLocation(), deliveryLocation);
    }


    @And("group order {string} should have {int} order")
    public void groupOrderShouldHaveOneOrder(String groupOrderCode, int groupOrderSize) throws NoSuchElementException {
        groupOrder = groupOrderFinder.findByCode(groupOrderCode).orElseThrow(() -> new NoSuchElementException("Element not found"));
        assertEquals(groupOrderSize, groupOrder.getSize());
    }

    @Then("the price of {string}'s order is {double}")
    public void thePriceOfSOrderIs(String username, double price) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(username).orElseThrow(() -> new NoSuchElementException("Element not found"));
        SimpleOrder order = subOrderManager.locateSubOrder(groupOrder, campusUser).orElseThrow(() -> new NoSuchElementException("Element not found"));
        assertEquals(order.getPrice(), price, 0.1);

    }
    @When("{string} closes the group order")
    public void requestsToCloseTheGroupOrder(String username) throws NoSuchElementException {
        campusUser = campusUserFinder.findByName(username).orElseThrow(() -> new NoSuchElementException("Element not found"));
        groupOrder.closeGroupOrder();
    }
}