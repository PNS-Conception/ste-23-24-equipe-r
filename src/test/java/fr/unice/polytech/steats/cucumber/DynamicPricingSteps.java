package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cart.CartService;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantStatus;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserStatus;
import io.cucumber.java.da.Men;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicPricingSteps {

    CampusUser campusUser;
    Restaurant restaurant;

    Menu menu;

    CartService cartService;



    @Given("a CampusUser with a profile indicating {string} as their user type.")
    public void a_campus_user_with_a_profile_indicating_as_their_user_type(String string) {
        for (CampusUserStatus status : CampusUserStatus.values()) {
            if (status.toString().equals(string)) {
                campusUser = new CampusUser("CampusUser", status);
            }
        }
    }
    @Given("a restaurant that is {string}")
    public void a_restaurant_that_is(String string) {
        for(RestaurantStatus status : RestaurantStatus.values()){
            if(status.toString().equals(string)){
                restaurant = new Restaurant("Restaurant", status);
            }
        }
    }
    @When("processing a payment for a {string} with the following {string} base on the user type")
    public void processing_a_payment_for_a_meal_with_the_following_base_on_the_user_type(String menuName, String price) {
        menu = new Menu(menuName, 10);
        menu.setPriceForUserType(campusUser.getStatus(), Double.parseDouble(price));
        restaurant.getMenus().add(menu);
        assertTrue(restaurant.getMenus().contains(menu));
        assertFalse(menu.getCampusUserStatusPrice().isEmpty());
        campusUser.getCart().getMenuMap().put(menu, 1);
    }
    @Then("the system calculate the final price as {string}")
    public void the_system_calculate_the_final_price_as(String finalPrice) {
        cartService = new CartService(campusUser.getCart());
        assertEquals(cartService.getPriceForUserType(campusUser), Double.parseDouble(finalPrice));
    }
}
