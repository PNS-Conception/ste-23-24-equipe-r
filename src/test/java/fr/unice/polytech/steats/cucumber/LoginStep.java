package fr.unice.polytech.steats.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.unice.polytech.steats.model.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class LoginStep {
    CampusUser user = new CampusUser();
    List<String> appOptions= List.of("create order option", "view order option", "cancel order option");

    @Given("{string} is on the login page")
    public void that_is_on_the_login_page(String string) {
        // Write code here that turns the phrase above into concrete actions
        user = new CampusUser(string,21,"password");
    }
    @When("{string} enters the username : {string}")
    public void enters_the_username(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(string, user.getName());
        assertEquals(Integer.parseInt(string2),user.getId());
    }
    @When("{string} enters the password : {string}")
    public void enters_the_password(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(string, user.getName());
        assertEquals(string2,user.getPassword());
    }

    @Then("the {string} should be visible")
    public void the_should_be_visible(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(appOptions.contains(string));
    }



}
