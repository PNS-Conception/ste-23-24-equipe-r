package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RatingAndViewRestaurantRatingSteps {

    Restaurant restaurant = new Restaurant("Restaurant A");;
    CampusUser campusUser1 = new CampusUser();
    CampusUser campusUser2 = new CampusUser();
    RatingSystem ratingSystem = new RatingSystem();

    /////////////////////////////////////////// Scenario 1 ///////////////////////////////////////////

    @Given("Restaurant A has a {string} of rating")
    public void restaurant_a_has_a_of_rating(String list) {
        List<Double> listOfRating = new ArrayList<>();
        String[] valueArray = list.split(", ");
        for (String value : valueArray) {
            listOfRating.add(Double.parseDouble(value));
        }
        ratingSystem.getRestaurantRatings().put(restaurant.getId(), listOfRating);
    }

    @When("the CampusUser1 rates the restaurant {int} out of 5")
    public void the_campus_user_rates_the_restaurant_stars(int rating) {
        campusUser1.rateRestaurantByUser(ratingSystem, restaurant.getId(), rating);
    }

    @Then("the rating of this restaurant should be {double} out of 5")
    public void the_rating_of_this_restaurant_should_be_star(double expectedRating) {
        Double actualRating = ratingSystem.averageRating(restaurant.getId());
        assertEquals(actualRating, expectedRating);
    }

    /////////////////////////////////////////// Scenario 2 ///////////////////////////////////////////
    @When("the CampusUser2 checks the rating of the restaurant")
    public void the_campus_user_checks_the_rating_of_the_restaurant() {
        assertFalse(campusUser2.getRestaurantRatings(ratingSystem).isEmpty());
        assertTrue(campusUser2.getRestaurantRatings(ratingSystem).containsKey(restaurant.getId()));

    }


}
