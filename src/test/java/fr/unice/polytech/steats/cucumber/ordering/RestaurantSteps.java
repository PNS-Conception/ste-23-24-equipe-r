package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.exceptions.restaurant.AlreadyExistingRestaurantException;
import fr.unice.polytech.steats.restaurant.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestaurantSteps {
    Restaurant restaurant;
    RestaurantRegistry restaurantRegistry;
    public RestaurantSteps(FacadeContainer container){
        this.restaurantRegistry = container.restaurantRegistry;
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
    @Given("timeslot {string} of the restaurant {string} has capacity {int}")
    public void timeslotHasCapacity(String dateTimeString, String restaurantName, int capacity) {
        restaurant = restaurantRegistry.findByName(restaurantName).get();
        LocalDateTime timeslotDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        restaurant.getSchedule().addTimeslot(new Timeslot(timeslotDateTime, capacity));
    }

}
