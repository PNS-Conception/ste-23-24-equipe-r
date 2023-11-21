package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.grouporder.GroupOrder;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRegistry;
import fr.unice.polytech.steats.order.grouporder.GroupOrderService;
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

import static org.junit.Assert.*;

public class GroupOrderSteps {
    GroupOrder groupOrder;
    String groupOrderCode;
    CampusUser campusUser;
    Restaurant restaurant;
    CampusUserRegistry campusUserRegistry;
    RestaurantRegistry restaurantRegistry;
    GroupOrderRegistry groupOrderRegistry;
    GroupOrderService groupOrderService;
    TimeSlot timeSlot;
    LocalTime deliveryTime;
    DeliveryLocation deliveryLocation;

    public GroupOrderSteps(FacadeContainer container){
        campusUserRegistry = container.campusUserRegistry;
        restaurantRegistry = container.restaurantRegistry;
        groupOrderRegistry = container.groupOrderRegistry;
        groupOrderService = container.groupOrderService;
    }


    @And("a group order exists of user {string} with restaurant {string} and delivery time {string} and location {string}")
    public void aGroupOrderExistsWithTheCodeOfUser(String campusUserName, String restaurantName,String deliveryTimeString, String deliveryLocationString) {
        deliveryTime = LocalTime.parse(deliveryTimeString);
        deliveryLocation = DeliveryLocation.getByName(deliveryLocationString);
        groupOrder = groupOrderRegistry.register(campusUser,deliveryTime,deliveryLocation);
        groupOrderCode = groupOrder.getGroupOrderCode();
        campusUser = campusUserRegistry.findByName(campusUserName).get();
        restaurant = restaurantRegistry.findByName(restaurantName).get();
    }

    @And("chooses timeslot {string} of the restaurant {string} and delivery location {string} for group order")
    public void chooseAvailableTimeslotAndDeliveryLocation(String timeSlotString, String restaurantName,
                                                           String delivLocation) {
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        LocalTime openingTime = LocalTime.parse(timeSlotString);
        timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }


    @When("{string} requests to create a group order")
    public void requestsToCreateAGroupOrder(String user) {
        campusUser = campusUserRegistry.findByName(user).get();
    }

    @Then("a group order is created with a unique code")
    public void aGroupOrderIsCreatedWithAUniqueCode() {
        groupOrderRegistry.register(campusUser,LocalTime.now(),deliveryLocation);
    }

    @And("the group order is in {string} status")
    public void theGroupOrderIsInStatus(String status) {
        boolean isOpen = status.equals("open");
        assertEquals(isOpen, groupOrder.isOpen());
    }

    @When("{string} joins the group order using the code")
    public void joinsTheGroupOrder(String userName) {
        groupOrder = groupOrderRegistry.findByCode(groupOrderCode).get();
        campusUser = campusUserRegistry.findByName(userName).get();
    }

    @And("{string} orders and pays for {int} x {string}")
    public void ordersAndPaysForX(String userName, int quantity, String menuName) throws EmptyCartException, PaymentException, NonExistentTimeSlot, InsufficientTimeSlotCapacity, NonExistentGroupOrder, ClosedGroupOrderException, DeliveryDateNotAvailable {
        campusUser = campusUserRegistry.findByName(userName).get();
        Cart cart = campusUser.getCart();
        Menu menu = restaurant.getMenufromName(menuName);
        CartHandler cartHandler = new CartHandler(cart);
        cartHandler.addItem(menu, quantity);
        groupOrderService.addSubOrder(groupOrderCode, restaurant, campusUser, campusUser.getCart().getMenuMap());
    }

    @And("{string}'s order should be set with timeslot {string} and location {string}")
    public void sOrderShouldBeSetWithTimeslotAndLocation(String username, String timeslotString, String delivLocation) {
        campusUser = campusUserRegistry.findByName(username).get();
        LocalTime openingTime = LocalTime.parse(timeslotString);
        TimeSlot timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        DeliveryLocation deliveryLocation = DeliveryLocation.getByName(delivLocation);
        Order order = groupOrder.getSubOrders().get(0);
        assertEquals(order.getTimeSlot(), timeSlot);
        assertEquals(order.getDeliveryLocation(),deliveryLocation);
    }

    @And("the group order should have {int} order")
    public void groupOrderShouldHaveOneOrder(int groupOrderSize) {
        groupOrder = groupOrderRegistry.findByCode(groupOrderCode).get();
        assertEquals(groupOrderSize, groupOrder.getSize());
    }

    @Then("the price of {string}'s order is {double}")
    public void thePriceOfSOrderIs(String username, double price) {
        campusUser = campusUserRegistry.findByName(username).get();
        Order order = groupOrderService.locateOrder(groupOrder, campusUser).get();
        assertEquals(order.getPrice(), price, 0.1);

    }
    @When("{string} closes the group order")
    public void requestsToCloseTheGroupOrder(String username) {
        campusUser = campusUserRegistry.findByName(username).get();
        groupOrder.closeGroupOrder();
    }
}
