package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.service.RestaurantDao;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemoveRestaurantSteps {
    RestaurantDao RD = new RestaurantDao();
    @Given("The list of restaurants contains those restaurants")
    public void the_list_of_restaurants_contains_those_restaurants(DataTable dataTable) {
        for (int i = 0; i < dataTable.height(); i++) {
            RD.save(new Restaurant(dataTable.asList().get(i)));
        }
    }
    @When("The campus admin removes the restaurant named {string}")
    public void i_remove_the_restaurant_named(String string) {
        RD.delete(RD.findByName(string));
    }
    @Then("{string} should be removed from the list of restaurants")
    public void the_list_of_restaurants_should_only_contain(String string) {
        assert(RD.findByName(string) == null);
    }
}
