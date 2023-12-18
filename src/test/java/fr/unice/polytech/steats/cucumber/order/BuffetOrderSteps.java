package fr.unice.polytech.steats.cucumber.order;

import fr.unice.polytech.steats.cucumber.picosetup.FacadeContainer;
import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.entities.order.BuffetOrder;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.interfaces.users.CampusUserFinder;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderProcessing;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.BuffetOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantLocator;
import io.cucumber.java.en.And;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class BuffetOrderSteps {

    BuffetOrder order;
    final CampusUserFinder campusUserFinder;
    LocalDateTime deliveryTime;
    DeliveryLocation deliveryLocation;
    Restaurant restaurant;
    final OrderProcessing orderProcessing;
    final RestaurantLocator restaurantLocator;
    final BuffetOrderProcessingStrategy buffetOrderProcessingStrategy;


    public BuffetOrderSteps(FacadeContainer container){
        this.campusUserFinder = container.campusUserRegistry;
        this.orderProcessing = container.orderProcessing;
        this.restaurantLocator = container.restaurantLocator;
        this.buffetOrderProcessingStrategy = container.buffetOrderProcessingStrategy;
    }
    @And("chooses delivery time {string} of the restaurant {string} and delivery location {string} for the buffet")
    public void chooseAvailableTimeslotAndDeliveryLocation(String dateTimeString, String restaurantName, String delivLocation) throws NoSuchElementException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        this.deliveryTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        deliveryLocation = DeliveryLocation.getByName(delivLocation);
    }
    @And("{string} confirms and pays for the buffet order for {int} participants")
    public void confirmsAndPaysForTheBuffetOrder(String username, int recipientNumber) throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        CampusUser campusUser = campusUserFinder.findByName(username).get();
        orderProcessing.setOrderProcessingStrategy(buffetOrderProcessingStrategy);
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .recipientNumber(recipientNumber)
                .build();
        order = (BuffetOrder)orderProcessing.process(orderDetails);
    }
    @And("the order status is {string} for the buffet order")
    public void theOrderStatusIs(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        assertEquals(order.getStatus(), orderStatus);
    }
    @And("the price of the buffet order is {double}")
    public void thePriceOfSOrderIs(double price) {
        assertEquals(price, order.getPrice(), 0.01);
    }
}
