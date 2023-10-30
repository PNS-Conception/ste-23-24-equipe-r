package fr.unice.polytech.steats.rating;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RatingSystem {
    private Map<UUID, RatingLevel> restaurantRatings;
    private Map<UUID, RatingLevel> deliveryPersonRatings;

    public RatingSystem(){
        restaurantRatings= new HashMap<>();
        deliveryPersonRatings= new HashMap<>();
    }

    ///// getters
    public Map<UUID, RatingLevel> getRestaurantRatings() {
        return restaurantRatings;
    }
    public Map<UUID, RatingLevel> getDeliveryPersonRatings() {
        return deliveryPersonRatings;
    }



    public RatingLevel averageRating(UUID id,RatingLevel ratingLevel){
        if (restaurantRatings.containsKey(id)) {
            int averageOrdinal = (restaurantRatings.get(id).ordinal() + ratingLevel.ordinal()) / 2;
            return RatingLevel.values()[averageOrdinal];
        } else {
            int averageOrdinal = (deliveryPersonRatings.get(id).ordinal() + ratingLevel.ordinal()) / 2;
            return RatingLevel.values()[averageOrdinal];
        }
    }


    public void rateRestaurant(UUID restaurantID, RatingLevel ratingLevel) {
        if (restaurantRatings.containsKey(restaurantID)) {
            restaurantRatings.replace(restaurantID, averageRating(restaurantID, ratingLevel));
        }
        else {
            restaurantRatings.put(restaurantID, ratingLevel);
        }
    }


    public void rateDeliveryPerson(UUID deliveryId, RatingLevel ratingLevel) {
        if (deliveryPersonRatings.containsKey(deliveryId)) {
            deliveryPersonRatings.replace(deliveryId, averageRating(deliveryId, ratingLevel));
        }
        else {
            deliveryPersonRatings.put(deliveryId, ratingLevel);
        }
    }
}

