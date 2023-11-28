package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;

public class CustomizeMenu {
    CampusUser CU ;
    Menu UserMenu ;
    @Given("a logged in campus user {string}")
    public void a_logged_in_campus_user(String string) {
        CU=new CampusUser(string);
    }

    @Given("a chosen Menu {string} with the price {int}")
    public void a_chosen_menu(String name ,int price) {
        System.out.println(name);
        UserMenu = new Menu(name,price);
        Map<String, List<String>> options = new HashMap<>();

        options.put("sauce",new ArrayList<>(Arrays.asList("white sauce", "red sauce")));
        options.put("add-ons",new ArrayList<>(Arrays.asList("grilled vegetables", "mushed potatoes")));
        options.put("dessert",new ArrayList<>(Arrays.asList("cheese cake", "pineapple cake")));

        UserMenu.setOptions(options);

    }

    @Given("a list of possible modifications for each menu item {string}, {string}, {string}")
    public void a_list_of_possible_modifications_for_each_menu_item(String string, String string2, String string3) {
        System.out.println("options: " + UserMenu.getOptions());
    }

    @When("the user chooses the sauce {string}, the dessert {string}, and the add-ons {string}")
    public void the_user_chooses_the_sauce_the_dessert_and_the_add_ons(String string, String string2, String string3) {
        Map<String, String> SelectedOptions = new HashMap<>() {{
            put("sauce", string);
            put("dessert", string2);
            put("add-ons", string3);
        }};
        UserMenu.setSelectedOptions(SelectedOptions);

    }

    @Then("the menu is changed")
    public void the_menu_is_changed() {
        CU.addMenuToCart(UserMenu);
    }


    @When("the user provides invalid menu customization choices sauce {string}, dessert {string},add-ons {string}")
    public void the_user_provides_invalid_menu_customization_choices_sauce_dessert_add_ons(String sauce, String dessert, String add_ons) {
            assertFalse(UserMenu.getOptions().get("dessert").contains(dessert));
            assertFalse(UserMenu.getOptions().get("sauce").contains(sauce));
            assertFalse(UserMenu.getOptions().get("add-ons").contains(sauce));
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        System.out.println("you provided a not existing dessert option");
        System.out.println("you provided a not existing sauce option");
        System.out.println("you provided a not existing add-ons option");
    }

    @Then("the menu remains unchanged")
    public void the_menu_remains_unchanged() {

    }



}
