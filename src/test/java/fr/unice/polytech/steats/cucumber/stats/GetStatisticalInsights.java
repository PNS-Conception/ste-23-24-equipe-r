package fr.unice.polytech.steats.cucumber.stats;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.stats.StatisticsManager;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetStatisticalInsights {

    User user;

    Order order;

    Restaurant restaurant;

    DeliveryLocation deliveryLocation1;
    StatisticsManager statisticsManager;


    @Given("an Order has been created but not {string}")
    public void an_Order_has_been_created_but_not(String status) {
        order= new Order(restaurant,null,null,null,null);
        assertNotEquals(order.getStatus().toString(), status);
    }
    @When("a {string} retrieves statistical data on order volumes")
    public void a_retrieves_statistical_data_on_order_volumes_for_the_current_time_period(String name) {
        user = new User(name);
    }
    @Then("the Order is included in the order volume data")
    public void the_Order_is_included_in_the_order_volume_data_for_the_current_period() throws NoOrdersPlacedException {
        statisticsManager = new StatisticsManager();
        assertTrue(statisticsManager.getOrderVolumesOverTime().contains(order));
    }


    @Given("an Order from a {string} has been created but not {string}")
    public void an_order_from_a_restaurant_has_been_created_but_not(String restaurantName,String status) {
        restaurant = new Restaurant(restaurantName);
        order = new Order(restaurant,null,null,null,null);
        assertNotEquals(order.getStatus().toString(), status);
    }
    @When("a {string} retrieves statistical data on order volumes per restaurant")
    public void a_retrieves_statistical_data_on_order_volumes_per_restaurant(String name) {
        user = new User(name);
    }
    @Then("the Order is included in the order volume data per restaurant")
    public void the_order_is_included_in_the_order_volume_data_per_restaurant() throws NoOrdersPlacedException {
        StatisticsManager statisticsManager = new StatisticsManager();
        assertTrue(statisticsManager.getOrderVolumesOverTime().contains(order));
        assertTrue(statisticsManager.getRestaurantOrderVolume(restaurant).contains(order));
    }


    @Given("an Order from {string} is created by a {string} with the delivery location {string}")
    public void an_order_from_is_created_by_a_with_the_delivery_location(String restaurantName, String userName, String deliveryLocation) {
        restaurant = new Restaurant(restaurantName);
        deliveryLocation1 = DeliveryLocation.getByName(deliveryLocation);
        order = new Order(restaurant,(new CampusUser(userName)),null,deliveryLocation1,null);
    }
    @Given("the status is not {string}")
    public void the_status_is_not(String status) {
        assertNotEquals(order.getStatus().toString(), status);
    }
    @When("User retrieves statistical data on order volumes per delivery location")
    public void user_retrieves_statistical_data_on_order_volumes_per_delivery_location() {
        statisticsManager= new StatisticsManager();


    }
    @Then("the {string} location is included in the delivery location data")
    public void the_order_is_included_in_the_order_volume_data_per_delivery_location(String location) throws NoOrdersPlacedException {
        assertEquals(statisticsManager.getDeliveryLocation(order), DeliveryLocation.getByName(location));
    }


}
