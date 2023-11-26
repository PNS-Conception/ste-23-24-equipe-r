package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.Timeslot;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrderSteps {
    Order order;
    CampusUserRegistry campusUserRegistry;
    LocalDateTime deliveryTime;
    DeliveryLocation deliveryLocation;
    Restaurant restaurant;
    OrderManager orderManager;
    RestaurantRegistry restaurantRegistry;

    public OrderSteps(FacadeContainer container){
        this.campusUserRegistry = container.campusUserRegistry;
        this.orderManager = container.orderManager;
        this.restaurantRegistry = container.restaurantRegistry;
    }

    @And("chooses delivery time {string} of the restaurant {string} and delivery location {string}")
    public void chooseAvailableTimeslotAndDeliveryLocation(String dateTimeString, String restaurantName, String delivLocation) {
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        LocalDateTime deliveryDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.deliveryTime = deliveryDateTime;
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }

    @And("{string} confirms and pays for the cart")
    public void confirmsAndPaysForTheCart(String customerName) throws PaymentException,
            NonExistentTimeSlot, InsufficientTimeSlotCapacity, EmptyCartException, DeliveryDateNotAvailable {

        CampusUser campusUser = campusUserRegistry.findByName(customerName).get();
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
