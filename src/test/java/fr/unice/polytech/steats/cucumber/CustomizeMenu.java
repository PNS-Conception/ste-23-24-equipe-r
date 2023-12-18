package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.MenuComments.CommentsRegistry;
import fr.unice.polytech.steats.restaurant.MenuComments.MenuComment;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.*;

import static java.util.Map.entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomizeMenu {
    CampusUser CU ;
    Menu UserMenu ;
    Restaurant restaurantMock = Mockito.mock(Restaurant.class);

    MenuComment menuComment;

    final CommentsRegistry commentsRegistry;
    public CustomizeMenu(FacadeContainer container) {
        commentsRegistry=container.commentsRegistry;
    }

    @Given("a logged in campus user {string}")
    public void a_logged_in_campus_user(String string) {
        CU=new CampusUser(string);
    }

    @Given("a chosen Menu {string} with the price {int}")
    public void a_chosen_menu(String name ,int price) {
        UserMenu = new Menu(name,price);
        Map<String, Map<String,Integer>> options = new HashMap<>();

        options.put("sauce",Map.ofEntries(entry("white sauce",12), entry("red sauce",10)));
        options.put("add-ons",Map.ofEntries(entry("grilled vegetables",10), entry("mushed potatoes",2)));
        options.put("dessert",Map.ofEntries(entry("cheese cake",18), entry("pineapple cake",20)));

        UserMenu.setOptions(options);

    }

    @Given("a list of possible modifications for each menu item {string}, {string}, {string}")
    public void a_list_of_possible_modifications_for_each_menu_item(String string, String string2, String string3) {
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

    @Then("the selected options are added and their price is calculated")
    public void the_menu_is_changed() {
        UserMenu.setOptionsPrice(UserMenu.calculateNewPrice());
        double OptionsPrice=0;
        for(String option : UserMenu.getSelectedOptions().keySet())
            OptionsPrice+=UserMenu.getOptions().get(option).get(UserMenu.getSelectedOptions().get(option));
        assertEquals(UserMenu.calculateNewPrice(),OptionsPrice,0.00001);
        CU.getCart().addMenu(restaurantMock, UserMenu, 1);
    }


    @When("the user provides invalid menu customization choices sauce {string}, dessert {string},add-ons {string}")
    public void the_user_provides_invalid_menu_customization_choices_sauce_dessert_add_ons(String sauce, String dessert, String add_ons) {
            assertFalse(UserMenu.getOptions().get("dessert").keySet().contains(dessert));
            assertFalse(UserMenu.getOptions().get("sauce").keySet().contains(sauce));
            assertFalse(UserMenu.getOptions().get("add-ons").keySet().contains(add_ons));
    }


    @Then("the menu remains unchanged")
    public void the_menu_remains_unchanged() {
        assertEquals(UserMenu.getSelectedOptions().size(),0);
        assertEquals(UserMenu.getOptionsPrice(),0,0.0001);
    }


    @When("the user writes a comment {string}")
    public void the_user_submits_the_comment(String comment) {
        menuComment = commentsRegistry.register(CU,UserMenu,comment);

    }

    @Then("the menu is commented")
    public void the_menu_is_commented() {
        MenuComment menuComment1 = commentsRegistry.findById(menuComment).orElseThrow(()->new NoSuchElementException("Element not found"));
        assertEquals(menuComment1.getCommentId(),menuComment.getCommentId());
    }



}
