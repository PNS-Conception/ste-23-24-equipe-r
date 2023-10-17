package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.model.CampusUser;
import fr.unice.polytech.steats.model.Menu;
import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.repository.RestaurantDao;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AddMenuToCart {
    CampusUser user;
    Restaurant restaurant;
    RestaurantDao restaurantRegistry;

    @Given("{string} is a campus user and a restaurant {string} who has a menu {string}")
    public void is_a_campus_user(String userName, String restaurantName, String menuName) {
        user = new CampusUser(userName);
        restaurant = new Restaurant(restaurantName);
        restaurant.addMenu(new Menu(menuName));
        restaurantRegistry = new RestaurantDao(Arrays.asList(restaurant));
    }

    @When("{string} selects a menu {string} from {string}")
    public void selects_a_menu_from_a_restaurant_restau(String userName, String menuName, String restaurantName) {
        Restaurant r = restaurantRegistry.getRestaurantFromName(restaurantName);
        Menu menuToBeAdded = r.getMenufromName(menuName);
        user.getCart().addToCart(menuToBeAdded);
    }

    @Then("the menu is added to the cart")
    public void the_menu_is_added_to_the_cart() {
        assertEquals(1, user.getCart().getMenusList().size());
    }
}
