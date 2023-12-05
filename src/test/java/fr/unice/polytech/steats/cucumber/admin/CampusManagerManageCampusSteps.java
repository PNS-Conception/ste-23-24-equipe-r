package fr.unice.polytech.steats.cucumber.admin;

import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.DeliveryPersonRegistry;
import fr.unice.polytech.steats.users.User;
import fr.unice.polytech.steats.users.UserRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class CampusManagerManageCampusSteps {

    final RestaurantRepository restaurantRepository;
    DeliveryPersonRegistry deliveryPersonRegistry;
    final UserRepository userRepository;
    User deliveryPerson;

    public CampusManagerManageCampusSteps(FacadeContainer container){
        this.restaurantRepository = container.restaurantRepository;
        this.userRepository = container.userRepository;
    }

    @Given("the campus has {int} restaurants")
    public void the_campus_has_restaurants(Integer int1, DataTable restaurantTable) {
        for (int i = 0; i < int1; i++) {
            Restaurant restaurant = new Restaurant(restaurantTable.row(i).get(0));
            restaurantRepository.save(restaurant, restaurant.getId());
        }
    }
    @Given("a restaurant {string} match the requirements")
    public void a_restaurant_match_the_requirements(String string) {
        assertTrue(restaurantRepository.checkRequirements(new Restaurant(string)));
    }
    @When("the campus admin attempts to add a new restaurant {string}")
    public void the_campus_admin_attempts_to_add_a_new_restaurant(String res) {
        Restaurant restaurant = new Restaurant(res);
        restaurantRepository.save(restaurant, restaurant.getId());
    }
    @Then("the campus should have {int} restaurants")
    public void the_campus_should_have_restaurants(int int1) {
        assertEquals(int1, restaurantRepository.count());
    }
    @And("the restaurant {string} should not be added the campus")
    public void the_restaurant_should_not_be_in_the_campus(String string) {
        assertNull(restaurantRepository.getRestaurantByName(string));
    }

    @Then("the restaurant {string} should be in the campus")
    public void the_restaurant_should_be_in_the_campus(String string) {
        assertNotNull(restaurantRepository.getRestaurantByName(string));
    }

    @When("The campus admin removes the restaurant {string}")
    public void the_campus_admin_removes_the_restaurant(String restaurantName) {
        restaurantRepository.deleteById(restaurantRepository.getRestaurantByName(restaurantName).getId());
    }

    @And("the restaurant {string} should be removed from the campus")
    public void theRestaurantShouldBeRemovedFromTheCampus(String restaurantName) {
        assertNull(restaurantRepository.getRestaurantByName(restaurantName));
    }

    @Given("a delivery person {string}")
    public void a_delivery_person_match_the_requirements(String deliveryPersonName) {
        deliveryPerson = new User(deliveryPersonName);
    }
    @When("the campus admin attempts to add a new delivery person")
    public void the_campus_admin_attempts_to_add_a_new_delivery_person() {
        deliveryPersonRegistry = new DeliveryPersonRegistry(userRepository);
        deliveryPersonRegistry.register(deliveryPerson);
    }
    @Then("the campus should have {int} delivery person")
    public void the_campus_should_have_delivery_person(int nb) {
        assertEquals(nb, userRepository.count());
    }
}
