package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.rating.RatingLevel;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class RatingAndViewdeliveryPersonRatingSteps {

    DeliveryPerson deliveryPerson = new DeliveryPerson();
    CampusUser campusUser1 = new CampusUser();
    CampusUser campusUser2 = new CampusUser();
    RatingSystem ratingSystem = new RatingSystem();

    /////////////////////////////////////////// Scenario 1 ///////////////////////////////////////////
    @Given("Delivery Person is rated at {int} stars")
    public void delivery_person_is_rated_at_stars(Integer rating) {
        RatingLevel ratingLevel = RatingLevel.values()[rating - 1];
        ratingSystem.getDeliveryPersonRatings().put(deliveryPerson.getId(), ratingLevel);
        System.out.println(deliveryPerson.getName()+" rating = " + ratingSystem.getDeliveryPersonRatings().get(deliveryPerson.getId())); // 2 starts
    }
    @When("the CampusUser1 rates the delivery person {int} stars")
    public void the_campus_user1_rates_the_delivery_person_stars(Integer rating) {
        RatingLevel ratingLevel = RatingLevel.values()[rating - 1];
        System.out.println("User's rating = " + ratingLevel); // 4 stars (new rating)
        campusUser1.rateDeliveryPersonByUser(ratingSystem, deliveryPerson.getId(), ratingLevel);
    }
    @Then("the rating of this delivery person should be {int} stars")
    public void the_rating_of_this_delivery_person_should_be_stars(Integer expectedRating) {
        RatingLevel actualRating = ratingSystem.getDeliveryPersonRatings().get(deliveryPerson.getId());
        assertEquals(RatingLevel.values()[expectedRating - 1], actualRating);
        System.out.println("Actual"+ deliveryPerson.getName()+ " rating = " + actualRating); // 3 stars (average)
    }


    /////////////////////////////////////////// Scenario 2 ///////////////////////////////////////////
    @When("the CampusUser2 checks the rating of the delivery person")
    public void the_campus_user2_checks_the_rating_of_the_delivery_person() {
        assertFalse(campusUser2.getDeliveryPersonRatings(ratingSystem).isEmpty());
        assertTrue(campusUser2.getDeliveryPersonRatings(ratingSystem).containsKey(deliveryPerson.getId()));
    }

}
