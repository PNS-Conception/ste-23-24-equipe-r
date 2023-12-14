package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserFinder;
import io.cucumber.java.en.And;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class OrderSteps {
    SimpleOrder order;
    final CampusUserFinder campusUserFinder;
    LocalDateTime deliveryTime;
    DeliveryLocation deliveryLocation;
    Restaurant restaurant;
    final OrderProcessing orderProcessing;
    final RestaurantLocator restaurantLocator;

    public OrderSteps(FacadeContainer container){
        this.campusUserFinder = container.campusUserRegistry;
        this.orderProcessing = container.orderProcessing;
        this.restaurantLocator = container.restaurantLocator;
    }

    @And("chooses delivery time {string} of the restaurant {string} and delivery location {string}")
    public void chooseAvailableTimeslotAndDeliveryLocation(String dateTimeString, String restaurantName, String delivLocation) throws NoSuchElementException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        this.deliveryTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }

    @And("{string} confirms and pays for the cart")
    public void confirmsAndPaysForTheCart(String customerName) throws PaymentException,
            EmptyCartException, DeliveryDateNotAvailable, NoSuchElementException {

        CampusUser campusUser = campusUserFinder.findByName(customerName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        order = orderProcessing.process(orderDetails);
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
