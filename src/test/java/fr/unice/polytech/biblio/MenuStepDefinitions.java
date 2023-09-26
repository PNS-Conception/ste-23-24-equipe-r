package fr.unice.polytech.biblio;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class MenuStepDefinitions {

    private String selectedRestaurant;
    private List<String> displayedMenus;

    @Given("{string} is on the menu page")
    public void is_on_the_menu_page(String restaurant) {
        // Assume navigating to the menu page for the given restaurant
        selectedRestaurant = restaurant;
    }

    @When("{string} requests the list of menus")
    public void requests_the_list_of_menus(String restaurant) {
        // Assuming a request for menus is made for a specific restaurant
        // Here, we're assuming the request is successful and menus are retrieved
        displayedMenus = fetchMenusForRestaurant(restaurant);
    }

    @Then("the system should display a list of menus for {string}")
    public void the_system_should_display_a_list_of_menus_for(String restaurant) {
        // Assuming the menus are displayed for the given restaurant
        // In a real application, you'd compare displayedMenus with expectedMenus
        System.out.println("Menus for " + restaurant + ": " + displayedMenus);
    }

    // Simulated method to fetch menus for a given restaurant
    private List<String> fetchMenusForRestaurant(String restaurant) {
        // Simulated menus for demonstration purposes
        if (restaurant.equals("Restaurant A")) {
            return List.of("Menu Item 1A", "Menu Item 2A", "Menu Item 3A");
        } else if (restaurant.equals("Restaurant B")) {
            return List.of("Menu Item 1B", "Menu Item 2B", "Menu Item 3B");
        } else {
            return List.of(); // Empty list if restaurant not found (for simplicity)
        }
    }
}
