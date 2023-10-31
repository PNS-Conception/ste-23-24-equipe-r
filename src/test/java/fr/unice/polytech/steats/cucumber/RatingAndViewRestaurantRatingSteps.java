package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.rating.RatingLevel;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class RatingAndViewRestaurantRatingSteps {

    Restaurant restaurant = new Restaurant("Restaurant A");;
    CampusUser campusUser1 = new CampusUser();
    CampusUser campusUser2 = new CampusUser();
    RatingSystem ratingSystem = new RatingSystem();

    /////////////////////////////////////////// Scenario 1 ///////////////////////////////////////////
    @Given("Restaurant A is rated at {int} stars")
    public void a_restaurant_is_rated_at_stars(int rating) {
        RatingLevel ratingLevel = RatingLevel.values()[rating - 1];
        ratingSystem.getRestaurantRatings().put(restaurant.getId(), ratingLevel);
        System.out.println(restaurant.getRestaurantName()+" rating = " + ratingSystem.getRestaurantRatings().get(restaurant.getId())); // 2 starts
    }

    @When("the CampusUser1 rates the restaurant {int} stars")
    public void the_campus_user_rates_the_restaurant_stars(int rating) {
        RatingLevel ratingLevel = RatingLevel.values()[rating - 1];
        System.out.println("User's rating = " + ratingLevel); // 4 stars (new rating)
        campusUser1.rateRestaurantByUser(ratingSystem, restaurant.getId(), ratingLevel);
    }

    @Then("the rating of this restaurant should be {int} stars")
    public void the_rating_of_this_restaurant_should_be_star(int expectedRating) {
        RatingLevel actualRating = ratingSystem.getRestaurantRatings().get(restaurant.getId());
        assertEquals(RatingLevel.values()[expectedRating - 1], actualRating);
        System.out.println("Actual"+ restaurant.getRestaurantName()+ " rating = " + actualRating); // 3 stars (average)
    }

    /////////////////////////////////////////// Scenario 2 ///////////////////////////////////////////
    @When("the CampusUser2 checks the rating of the restaurant")
    public void the_campus_user_checks_the_rating_of_the_restaurant() {
        assertFalse(campusUser2.getRestaurantRatings(ratingSystem).isEmpty());
        assertTrue(campusUser2.getRestaurantRatings(ratingSystem).containsKey(restaurant.getId()));

    }


}
