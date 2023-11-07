package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cart.CartService;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.cart.MenuRemovalFromCartException;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.AlreadyExistingRestaurantException;
import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.restaurant.*;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.CampusUserRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlaceOrder {
    CampusUser campusUser;
    Restaurant restaurant;
    Cart cart;
    Order order;
    CartService cartService;
    TimeSlot timeSlot;
    DeliveryLocation deliveryLocation;
    OrderRegistry orderRegistry;
    OrderRepository orderRepository;

    RestaurantRepository restaurantRepository;
    CampusUserRepository campusUserRepository;
    CampusUserRegistry campusUserRegistry;
    RestaurantRegistry restaurantRegistry;
    public PlaceOrder(RestaurantRepository restaurantRepository, RestaurantRegistry restaurantRegistry,
                      CampusUserRepository campusUserRepository ,CampusUserRegistry campusUserRegistry,
                      OrderRepository orderRepository, OrderRegistry orderRegistry){
        this.restaurantRepository = restaurantRepository;
        this.restaurantRegistry = restaurantRegistry;
        this.campusUserRepository = campusUserRepository;
        this.campusUserRegistry = campusUserRegistry;
        this.orderRepository = orderRepository;
        this.orderRegistry = orderRegistry;
    }

    @Before
    public void setUp(){
        restaurantRepository.deleteAll();
        campusUserRepository.deleteAll();
        orderRepository.deleteAll();
    }
    @Given("{string} is a campus user")
    public void isACampusUser(String username) throws AlreadyExistingUserException {
        campusUserRegistry.register(username);
    }
    @And("a restaurant {string} exists with the following details")
    public void aRestaurantExistsWithTheFollowingDetails(String restaurantName, DataTable dataTable)
            throws AlreadyExistingRestaurantException {
        List<Map<String, String>> restaurantDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = restaurantDetailsList.get(0);
        String openingTimeStr = map.get("Opening Time");
        String closingTimeStr = map.get("Closing Time");
        String capacityStr = map.get("Capacity");
        LocalTime openingTime = LocalTime.parse(openingTimeStr);
        LocalTime closingTime = LocalTime.parse(closingTimeStr);
        int capacity = Integer.parseInt(capacityStr);
        restaurantRegistry.register(restaurantName, openingTime, closingTime, capacity);
    }



    @And("the restaurant {string} has the following menus")
    public void a_restaurant_exists_with_the_following_menus(String restaurantName, DataTable dataTable) {
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        List<Map<String, String>> menus = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> menuData : menus) {
            String menuName = menuData.get("Menu Name");
            double price = Double.parseDouble(menuData.get("Price"));
            Menu menu = new Menu(menuName, price);
            restaurant.addMenu(menu);
        }
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
        cart = campusUser.getCart();
        assertEquals(cart.getPrice(), cartPrice, 0.01);
    }

    @Given("timeslot {string} has capacity {int}")
    public void timeslotHasCapacity(String timeSlotString, int capacity) {
        LocalTime openingTime = LocalTime.parse(timeSlotString);
        TimeSlot timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        timeSlot.setCapacity(capacity);
    }
    @And("chooses timeslot {string} and delivery location {string}")
    public void chooseAvailableTimeslotAndDeliveryLocation(String timeSlotString, String delivLocation) {
        LocalTime openingTime = LocalTime.parse(timeSlotString);
        timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }
    @And("{string} confirms and pays for the cart")
    public void confirmsAndPaysForTheCart(String customerName) throws PaymentException,
            NonExistentTimeSlot, InsufficientTimeSlotCapacity, EmptyCartException {
        campusUser = campusUserRegistry.findByName(customerName).get();
        order = orderRegistry.register(restaurant, campusUser, campusUser.getCart().getMenuMap(),
                timeSlot, deliveryLocation);
    }
    @Then("timeslot {string} should have capacity {int}")
    public void timeslotShouldHaveCapacity(String timeslotString, int capacity) {
        LocalTime openingTime = LocalTime.parse(timeslotString);
        timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        assertEquals(timeSlot.getCapacity(), capacity);
    }
    @And("the price of the order is {double}")
    public void thePriceOfSOrderIs(double price) {
        System.out.println("order price : "+order.getPrice());
        System.out.println("menus number : "+order.getTotalMenus());
        assertEquals(price, order.getPrice(), 0.01);
    }

    @And("the order status is {string}")
    public void theOrderStatusIs(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        assertEquals(order.getStatus(), orderStatus);
    }




}
