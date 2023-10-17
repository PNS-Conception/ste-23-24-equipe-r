package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.model.CampusUser;
import fr.unice.polytech.steats.model.Menu;
import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.service.RestaurantDao;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;

public class AddMenuToCart {
    CampusUser user;
    Restaurant restaurant;
    RestaurantDao restaurantRegistry;
    @Given("{string} is a campus user")
    public void is_a_campus_user(String userName) {
        user = new CampusUser(userName);
        restaurant = new Restaurant("restau");
        restaurant.addMenu(new Menu("Pizza"));
        restaurantRegistry = new RestaurantDao(Arrays.asList(restaurant));
    }

    @When("he selects a menu {string} from a restaurant {string}")
    public void selects_a_menu_from_a_restaurant_restau(String menuName, String restaurantName) {
        Restaurant r= restaurantRegistry.getRestaurantFromName(restaurantName);
        r.getMenufromName(menuName);
        user.addMenuToCart(r.getMenufromName(menuName));
    }
    @Then("the menu is added to the cart")
    public void the_menu_is_added_to_the_cart() {
        assert user.getCart().size() == 1;
    }

}
