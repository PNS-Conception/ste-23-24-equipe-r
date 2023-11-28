package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import io.cucumber.java.en.And;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class OrderSteps {
    SimpleOrder order;
    final CampusUserRegistry campusUserRegistry;
    LocalDateTime deliveryTime;
    DeliveryLocation deliveryLocation;
    Restaurant restaurant;
    final OrderManager orderManager;
    final RestaurantRegistry restaurantRegistry;

    public OrderSteps(FacadeContainer container){
        this.campusUserRegistry = container.campusUserRegistry;
        this.orderManager = container.orderManager;
        this.restaurantRegistry = container.restaurantRegistry;
    }

    @And("chooses delivery time {string} of the restaurant {string} and delivery location {string}")
    public void chooseAvailableTimeslotAndDeliveryLocation(String dateTimeString, String restaurantName, String delivLocation) throws NoSuchElementException {
        restaurant = restaurantRegistry.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        this.deliveryTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }

    @And("{string} confirms and pays for the cart")
    public void confirmsAndPaysForTheCart(String customerName) throws PaymentException,
            EmptyCartException, DeliveryDateNotAvailable, NoSuchElementException {

        CampusUser campusUser = campusUserRegistry.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        order = orderManager.process(restaurant, campusUser, campusUser.getCart().getMenuMap(),
                deliveryTime, deliveryLocation);
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
