package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.util.*;

public class BrowseMenuSteps {
    List<Restaurant> restaurantList;
    List<Menu> displayedMenus;
    String message;

    @Given("a list of campus restaurants with multiple menus")
    public void a_list_of_campus_restaurants_with_multiple_menus() {
        restaurantList = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant("R1", "Sushi Place");
        Restaurant restaurant2 = new Restaurant("R2", "Burger Joint");
        restaurant1.addMenu(new Menu("M1", "Sushi Menu"));
        restaurant2.addMenu(new Menu("M2", "Burger Menu"));
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
    }

    @Given("an empty list of campus restaurants")
    public void an_empty_list_of_campus_restaurants() {
        restaurantList = new ArrayList<>();
    }

    @Given("a list of campus restaurants with one menu")
    public void a_list_of_campus_restaurants_with_one_menu() {
        restaurantList = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant("R1", "Sushi Place");
        restaurant1.addMenu(new Menu("M1", "Sushi Menu"));
        restaurantList.add(restaurant1);
    }

    @Given("a list of campus restaurants where some restaurants have no menus")
    public void a_list_of_campus_restaurants_where_some_restaurants_have_no_menus() {
        restaurantList = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant("R1", "Sushi Place");
        Restaurant restaurant2 = new Restaurant("R2", "Burger Joint");
        restaurant1.addMenu(new Menu("M1", "Sushi Menu"));
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
    }

    @When("an internet user visits the restaurant page")
    public void an_internet_user_visits_the_restaurant_page() {
        displayedMenus = new ArrayList<>();
        message = null;
        if (restaurantList.isEmpty()) {
            message = "No restaurants available";
        } else {
            for (Restaurant restaurant : restaurantList) {
                List<Menu> menus = restaurant.getMenus();
                if (menus.isEmpty()) {
                    message = "Some restaurants have no menus available";
                } else {
                    displayedMenus.addAll(menus);
                }
            }
        }
    }

    @Then("the menus from different campus restaurants should be displayed")
    public void the_menus_from_different_campus_restaurants_should_be_displayed() {
        assertNotNull(displayedMenus);
        assertTrue(displayedMenus.size() > 1);
    }

    @Then("a message {string} should be displayed")
    public void a_message_should_be_displayed(String expectedMessage) {
        assertEquals(expectedMessage, message);
    }

    @Then("only the menu from that single restaurant should be displayed")
    public void only_the_menu_from_that_single_restaurant_should_be_displayed() {
        assertNotNull(displayedMenus);
        assertTrue(displayedMenus.size() == 1);
    }

    @Then("the menus from restaurants that have them should be displayed")
    public void the_menus_from_restaurants_that_have_them_should_be_displayed() {
        assertNotNull(displayedMenus);
        assertTrue(displayedMenus.size() > 0);
    }

    @Then("a message should indicate the restaurants with no menus available")
    public void a_message_should_indicate_the_restaurants_with_no_menus_available() {
        assertEquals("Some restaurants have no menus available", message);
    }
}
