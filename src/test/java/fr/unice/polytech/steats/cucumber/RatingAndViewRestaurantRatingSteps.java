package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.rating.RatingInfo;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;
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
        List<RatingInfo> listOfRatingInfo = new ArrayList<>();
        String[] valueArray = list.split(", ");
        for (String value : valueArray) {
            listOfRatingInfo.add(new RatingInfo(new User(),Double.parseDouble(value)));
        }
        ratingSystem.getRestaurantRatings().put(restaurant, listOfRatingInfo);
    }

    @When("the CampusUser1 rates the restaurant {int} out of 5")
    public void the_campus_user_rates_the_restaurant_stars(int rating) {
        ratingSystem.rateRestaurant(restaurant,campusUser1,(double) rating);
    }

    @Then("the rating of this restaurant should be {double} out of 5")
    public void the_rating_of_this_restaurant_should_be_star(double expectedRating) {
        Double actualRating = ratingSystem.averageRatingRestaurant(restaurant);
        assertEquals(actualRating, expectedRating);
    }

    /////////////////////////////////////////// Scenario 2 ///////////////////////////////////////////
    @When("the CampusUser2 checks the rating of the restaurant")
    public void the_campus_user_checks_the_rating_of_the_restaurant() {
        assertFalse(ratingSystem.getRestaurantRatings().isEmpty());
        assertTrue(ratingSystem.getRestaurantRatings().containsKey(restaurant));

    }


}
