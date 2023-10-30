package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.Cart;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateOrder {
    CampusUser campusUser;
    Order order;
    Cart cart;
    RestaurantRepository restaurantRepository = new RestaurantRepository();
    Restaurant restaurant;
    @Given("{string} is an authenticated CampusUser")
    public void iAmAnAuthenticatedCampusUser(String username) {
        campusUser = new CampusUser(username);
        cart = campusUser.getCart();
    }

    @And("he has the following items in his cart from restaurant {string}")
    public void iHaveTheFollowingItemsInMyCart(String restaurantName,DataTable table) {
        restaurant = new Restaurant(restaurantName);
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String menuName = columns.get("menuName");
            double price = Double.parseDouble(columns.get("price"));
            Menu menu = new Menu(menuName, price);
            campusUser.addMenuToCart(menu);
            restaurant.getMenus().add(menu);
        }
        restaurantRepository.save(restaurant, restaurant.getId());
    }

    @When("he chooses the available delivery location {string}")
    public void iChooseTheDeliveryLocation(String locationName) {
        order = new Order(campusUser);
        order.setDeliveryLocation(DeliveryLocation.LIBRARY);
        restaurant.getPendingOrders().add(order);
    }

    @And("he chooses the available slot {string}")
    public void iChooseTheAvailableDeliveryTime(String timeSlotString) {
        LocalTime startTime = LocalTime.parse(timeSlotString, DateTimeFormatter.ofPattern("HH:mm"));
        TimeSlot timeSlot = restaurantRepository.getRestaurantByOrder(order)
                .getSchedule().findTimeSlotByStartTime(startTime);
        order.setTimeSlot(timeSlot);
    }

    @Then("an order gets created using the cart")
    public void anOrderGetsCreatedUsingTheCart() {
        order.setOrderStatus(OrderStatus.CONFIRMED);
        assertNotNull(order);
        assertEquals(order.getCustomer(), campusUser);
        assertEquals(OrderStatus.CONFIRMED, order.getOrderStatus());
    }
}
