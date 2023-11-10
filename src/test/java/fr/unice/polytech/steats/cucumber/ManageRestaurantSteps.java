package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.Schedule;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManageRestaurantSteps {
    Restaurant restaurant;
    @Given("the owner of the restaurant {string} with a current menu including")
    public void the_owner_of_the_restaurant_with_a_current_menu_including(String restaurantName, DataTable menus) {
        restaurant = new Restaurant(restaurantName);
        for (int i = 0; i < menus.height(); i++) {
            Menu menu = new Menu(menus.row(i).get(0), Double.parseDouble(menus.row(i).get(1)));
            restaurant.addMenu(menu);
        }
    }
    @When("he adds to the list of menus")
    public void that_he_wants_to_add_and_to_the_menu(DataTable menus) {
        for (int i = 0; i < menus.height(); i++) {
            Menu menu = new Menu(menus.row(i).get(0), Double.parseDouble(menus.row(i).get(1)));
            restaurant.addMenu(menu);
        }
    }
    @Then("the menu should contain")
    public void the_menu_should_contain(DataTable dataTable) {
        for (int i = 0; i < dataTable.height(); i++) {
            assertNotNull(restaurant.getMenufromName(dataTable.row(i).get(0)));
        }
    }


    @Given("the owner of the restaurant {string} with current operating hours from {string} to {string} with a capacity of {int} dishes per ten minutes")
    public void theOwnerOfTheRestaurantWithCurrentOperatingHoursFromTo(String restaurantName, String openingTime, String closingTime, int capacity) {
        restaurant = new Restaurant(restaurantName);
        Schedule schedule = new Schedule(Schedule.parseTime(openingTime), Schedule.parseTime(closingTime), capacity);
        restaurant.setSchedule(schedule);
    }

    @When("he updates the operating hours to be from {string} to {string} with a capacity of {int} dishes per ten minutes for each slot")
    public void heUpdatesTheOperatingHours(String openingTime, String closingTime, int capacity) {
        Schedule newSchedule = new Schedule(Schedule.parseTime(openingTime), Schedule.parseTime(closingTime), capacity);
        restaurant.setSchedule(newSchedule);
    }

    @Then("the operating hours should be from {string} to {string} with a capacity of {int} dishes per ten minutes for each slot")
    public void theDisplayedOperatingHoursShouldBeFromTo(String openingTime, String closingTime, int capacity) {
        assertEquals(Schedule.parseTime(openingTime),restaurant.getSchedule().getOpeningTime());
        assertEquals(restaurant.getSchedule().getClosingTime(), Schedule.parseTime(closingTime));
        for(int i = 0; i < restaurant.getSchedule().getTimeSlots().size(); i++){
            assertEquals(restaurant.getSchedule().getTimeSlots().get(i).getCapacity(), capacity);
        }
    }

    @Given("the owner of the restaurant {string} with a current production capacity of {int} dishes per ten minutes")
    public void theOwnerOfTheRestaurantWithACurrentProductionCapacityOfDishesPerSlot(String restaurantName, int capacity) {
        restaurant = new Restaurant(restaurantName);
        restaurant.getSchedule().setCapacity(capacity);
    }

    @When("he updates the production capacity to {int} dishes per ten minutes")
    public void heUpdatesTheProductionCapacityToDishesPerSlot(int capacity) {
        restaurant.getSchedule().setCapacity(capacity);
    }

    @Then("the production capacity should be {int} dishes per ten minutes")
    public void theProductionCapacityShouldBeDishesPerSlot(int capacity) {
        for(int i = 0; i < restaurant.getSchedule().getTimeSlots().size(); i++){
            assertEquals(restaurant.getSchedule().getTimeSlots().get(i).getCapacity(), capacity);
        }
    }
}
