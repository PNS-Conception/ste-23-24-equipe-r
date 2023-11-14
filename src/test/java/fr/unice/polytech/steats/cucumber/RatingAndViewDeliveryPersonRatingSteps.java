package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.rating.RatingInfo;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RatingAndViewDeliveryPersonRatingSteps {
    User deliveryPerson = new User("DP1");
    CampusUser campusUser1 = new CampusUser();
    RatingSystem ratingSystem = new RatingSystem();

    @Given("Delivery Person DP1 has a {string} of ratings")
    public void delivery_person_dp1_has_a_of_ratings(String list) {
        List<RatingInfo> listOfRatingInfo = new ArrayList<>();
        String[] valueArray = list.split(", ");
        for (String value : valueArray) {
            listOfRatingInfo.add(new RatingInfo(new User(),Double.parseDouble(value)));
        }
        ratingSystem.getDeliveryPersonRatings().put(deliveryPerson.getId(), listOfRatingInfo);
    }
    @When("the CampusUser1 rates the delivery person with {int} out of 5")
    public void the_campus_user1_rates_the_delivery_person_with_out_of(int rating) {
        ratingSystem.rateDeliveryPerson(deliveryPerson.getId(),campusUser1, (double) rating);
    }
    @Then("the rating of this delivery person should be {double} out of 5")
    public void the_rating_of_this_delivery_person_should_be_out_of(double expectedRating) {
        Double actualRating = ratingSystem.averageRatingDeliveryPerson(deliveryPerson.getId());
        assertEquals(actualRating, expectedRating);
    }

    @When("the CampusUser2 checks the rating of the delivery person")
    public void the_campus_user2_checks_the_rating_of_the_delivery_person() {
        assertFalse(ratingSystem.getDeliveryPersonRatings().isEmpty());
        assertTrue(ratingSystem.getDeliveryPersonRatings().containsKey(deliveryPerson.getId()));
    }
}
