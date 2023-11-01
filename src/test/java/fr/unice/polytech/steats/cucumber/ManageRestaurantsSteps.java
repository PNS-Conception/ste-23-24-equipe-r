package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageRestaurantsSteps {

    RestaurantRepository restaurantRepository = new RestaurantRepository();

    @Given("the campus has {int} restaurants")
    public void the_campus_has_restaurants(Integer int1, DataTable restaurantTable) {
        for (int i = 0; i < restaurantTable.height(); i++) {
            Restaurant restaurant = new Restaurant(restaurantTable.row(i).get(0));
            restaurantRepository.save(restaurant, restaurant.getId());
        }
    }
    @Given("a restaurant {string} match the requirements")
    public void a_restaurant_match_the_requirements(String string) {
        assert restaurantRepository.checkRequirements(new Restaurant(string));
    }
    @When("the campus admin attempts to add a new restaurant {string}")
    public void the_campus_admin_attempts_to_add_a_new_restaurant(String res) {
        Restaurant restaurant = new Restaurant(res);
        restaurantRepository.save(restaurant, restaurant.getId());
    }
    @Then("the campus should have {int} restaurants")
    public void the_campus_should_have_restaurants(Integer int1) {
        assert restaurantRepository.count() == int1;
    }
    @And("the restaurant {string} should not be added the campus")
    public void the_restaurant_should_not_be_in_the_campus(String string) {
        assert restaurantRepository.getRestaurantByName(string) == null;
    }

    @Then("the restaurant {string} should be in the campus")
    public void the_restaurant_should_be_in_the_campus(String string) {
        assert restaurantRepository.getRestaurantByName(string) != null;
    }

    @When("The campus admin removes the restaurant {string}")
    public void the_campus_admin_removes_the_restaurant(String restaurantName) {
        for (Restaurant restaurant : restaurantRepository.findAll()) {
            System.out.println(restaurant.getRestaurantName());
        }
        restaurantRepository.deleteById(restaurantRepository.getRestaurantByName(restaurantName).getId());
    }

}
