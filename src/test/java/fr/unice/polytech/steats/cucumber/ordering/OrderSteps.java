package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import io.cucumber.java.en.And;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class OrderSteps {
    Order order;
    CampusUserRegistry campusUserRegistry;
    TimeSlot timeSlot;
    DeliveryLocation deliveryLocation;
    Restaurant restaurant;
    OrderRegistry orderRegistry;
    RestaurantRegistry restaurantRegistry;

    public OrderSteps(FacadeContainer container){
        this.campusUserRegistry = container.campusUserRegistry;
        this.orderRegistry = container.orderRegistry;
        this.restaurantRegistry = container.restaurantRegistry;
    }

    @And("chooses timeslot {string} of the restaurant {string} and delivery location {string}")
    public void chooseAvailableTimeslotAndDeliveryLocation(String timeSlotString, String restaurantName,
                                                           String delivLocation) {
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        LocalTime openingTime = LocalTime.parse(timeSlotString);
        timeSlot = restaurant.getSchedule().findTimeSlotByStartTime(openingTime).get();
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }

    @And("{string} confirms and pays for the cart")
    public void confirmsAndPaysForTheCart(String customerName) throws PaymentException,
            NonExistentTimeSlot, InsufficientTimeSlotCapacity, EmptyCartException {
        CampusUser campusUser = campusUserRegistry.findByName(customerName).get();
        order = orderRegistry.register(restaurant, campusUser, campusUser.getCart().getMenuMap(),
                timeSlot, deliveryLocation);
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
