package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AddMenuToCart {
    CampusUser user;
    Restaurant restaurant;
    RestaurantRepository restaurantRepository = new RestaurantRepository();

    @Given("{string} is a campus user")
    public void isACampusUser(String username) {
        user = new CampusUser(username);
    }

    @And("a restaurant {string} exists with the following menus")
    public void a_restaurant_exists_with_the_following_menus(String restaurantName, DataTable dataTable) {
        restaurant = new Restaurant(restaurantName);
        List<Map<String, String>> menus = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> menuData : menus) {
            String menuName = menuData.get("Menu Name");
            double price = Double.parseDouble(menuData.get("Price"));
            Menu menu = new Menu(menuName, price);
            restaurant.addMenu(menu);
        }
        restaurantRepository.save(restaurant, restaurant.getId());
    }

    @When("he selects a menu {string} from the restaurant")
    public void selects_a_menu_from_a_restaurant_restau(String menuName) {
        Menu menuToBeAdded = restaurant.getMenufromName(menuName);
        user.getCart().addToCart(menuToBeAdded);
    }

    @Then("the menu is added to the cart")
    public void the_menu_is_added_to_the_cart() {
        assertEquals(1, user.getCart().getMenusList().size());
    }
}
