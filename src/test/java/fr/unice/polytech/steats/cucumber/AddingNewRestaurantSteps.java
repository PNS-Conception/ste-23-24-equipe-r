package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.Exceptions.RestaurantAdditionException;
import fr.unice.polytech.steats.model.CampusAdmin;
import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.service.RestaurantDao;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class AddingNewRestaurantSteps {
    CampusAdmin CA ;
    Restaurant RT ;

    RestaurantDao RD = new RestaurantDao() ;

    @Given("the campus admin {string} is on the add restaurant page")
    public void theCampusAdminIsOnTheAddRestaurantPage(String arg0) {
        CA = new CampusAdmin(arg0);

    }
    @And("there is a new restaurant {string} to be added.")
    public void thereIsANewRestaurantToBeAdded(String arg0) {
        RT = new Restaurant(arg0);
    }
    @When("the campus admin {string} to add a restaurant named {string}")
    public void the_campus_admin_to_add_a_restaurant_named(String string, String string2) {

        assertEquals(CA.getName(),string);
        assertEquals(RT.getRestaurantName(),string2);


    }

    @When("The restaurant fills out all the necessary checklists.")
    public void the_restaurant_fills_out_all_the_necessary_checklists() {
        assertTrue(RT.checkRestaurant());

    }

    @Then("the restaurant {string} can be added successfully")
    public void the_restaurant_can_be_added_successfully(String string) {
        assertEquals(RT.getRestaurantName(),string);
        RD.save(RT);
    }

    @Then("the campus admin {string} should receive a confirmation message {string} indicating the successful addition of the restaurant {string}.")
    public void the_campus_admin_should_receive_a_confirmation_message_indicating_the_successful_addition_of_the_restaurant(String string, String string2, String string3) {

        assertEquals(CA.getName(),string);
        assertEquals(RT.getRestaurantName(),string3);
        CA.addNotification(string2);
    }

    @When("the campus admin attempts to add a restaurant named {string}")
    public void the_campus_admin_attempts_to_add_a_restaurant_named(String string) {
        assertEquals(RT.getRestaurantName(), string);
    }

    @When("The restaurant {string} doesn't fill all the application requirements.")
    public void the_restaurant_doesn_t_fill_all_the_application_requirements(String string) {
        System.out.println(RT.checkRestaurant());
        assertFalse(!(RT.checkRestaurant()));

    }

    @Then("the application will raise an error {string}.")
    public void theApplicationWillRaiseAnError(String arg0) {
        System.out.println("Raising an error ... ");
        RestaurantAdditionException exception = assertThrows(RestaurantAdditionException.class, () -> {
            RD.save(RT);
        });

        assertEquals(arg0, exception.getMessage());
    }
}
