package fr.unice.polytech.steats.cucumber;

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
    CampusUser campusUser2 = new CampusUser();
    RatingSystem ratingSystem = new RatingSystem();

    @Given("Delivery Person DP1 has a {string} of ratings")
    public void delivery_person_dp1_has_a_of_ratings(String list) {
        List<Double> listOfRating = new ArrayList<>();
        String[] valueArray = list.split(", ");
        for (String value : valueArray) {
            listOfRating.add(Double.parseDouble(value));
        }
        ratingSystem.getDeliveryPersonRatings().put(deliveryPerson.getId(), listOfRating);
        System.out.println(deliveryPerson.getName()+" rating = " + ratingSystem.getDeliveryPersonRatings().get(deliveryPerson.getId()));
    }
    @When("the CampusUser1 rates the delivery person with {int} out of 5")
    public void the_campus_user1_rates_the_delivery_person_with_out_of(int rating) {
        System.out.println("User's rating = " + rating);
        campusUser1.rateDeliveryPersonByUser(ratingSystem, deliveryPerson.getId(), rating);
    }
    @Then("the rating of this delivery person should be {double} out of 5")
    public void the_rating_of_this_delivery_person_should_be_out_of(double expectedRating) {
        Double actualRating = ratingSystem.averageRating(deliveryPerson.getId());
        assertEquals(actualRating, expectedRating);
        System.out.println("List of rating of this delivery person = " + ratingSystem.getDeliveryPersonRatings().get(deliveryPerson.getId()));
        System.out.println("Actual "+ deliveryPerson.getName()+ " rating = " + actualRating);
    }

    @When("the CampusUser2 checks the rating of the delivery person")
    public void the_campus_user2_checks_the_rating_of_the_delivery_person() {
        assertFalse(campusUser2.getDeliveryPersonRatings(ratingSystem).isEmpty());
        assertTrue(campusUser2.getDeliveryPersonRatings(ratingSystem).containsKey(deliveryPerson.getId()));
    }
}
