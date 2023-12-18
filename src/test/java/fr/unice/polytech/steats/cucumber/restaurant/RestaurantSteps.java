package fr.unice.polytech.steats.cucumber.restaurant;

import fr.unice.polytech.steats.cucumber.picosetup.FacadeContainer;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.restaurant.TimeSlot;
import fr.unice.polytech.steats.steatspico.exceptions.others.NoSuchElementException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.AlreadyExistingRestaurantException;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantRegistration;
import fr.unice.polytech.steats.steatspico.restaurant.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class RestaurantSteps {
    Restaurant restaurant;
    final RestaurantRegistration restaurantRegistration;
    RestaurantLocator restaurantLocator;
    public RestaurantSteps(FacadeContainer container){
        this.restaurantRegistration = container.restaurantRegistration;
        this.restaurantLocator = container.restaurantLocator;
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
        restaurantRegistration.register(restaurantName, openingTime, closingTime, capacity);
    }





    @And("the restaurant {string} has the following menus")
    public void a_restaurant_exists_with_the_following_menus(String restaurantName, DataTable dataTable) throws NoSuchElementException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        List<Map<String, String>> menus = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> menuData : menus) {
            String menuName = menuData.get("Menu Name");
            double price = Double.parseDouble(menuData.get("Price"));
            Menu menu = new Menu(menuName, price);
            restaurant.addMenu(menu);
        }
    }
    @Given("timeslot {string} of the restaurant {string} has capacity {int}")
    public void timeslotHasCapacity(String dateTimeString, String restaurantName, int capacity) throws NoSuchElementException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        LocalDateTime timeslotDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        restaurant.getSchedule().addTimeslot(new TimeSlot(timeslotDateTime, capacity));
    }

    @And("the restaurant {string} offers a buffet called {string} priced at {double} per participant")
    public void theRestaurantOffersABuffetCalledPricedAtPerParticipant(String restaurantName, String buffetName, double price) throws NoSuchElementException {
        Menu menu = new Menu(buffetName, price);
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        restaurant.addMenu(menu);
    }

    @And("timeslot {string} at {string} can accommodate a buffet for {int} participants")
    public void timeslotAtCanAccommodateABuffetForParticipants(String timeslotString, String restaurantName, int numberParticipants) throws NoSuchElementException {
        restaurant = restaurantLocator.findByName(restaurantName).orElseThrow(() -> new NoSuchElementException("Element not found"));
        LocalDateTime timeslotDateTime = LocalDateTime.parse(timeslotString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        restaurant.getSchedule().addTimeslot(new TimeSlot(timeslotDateTime, 20));
    }
}