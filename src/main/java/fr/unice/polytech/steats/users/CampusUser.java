package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.order.Cart;
import fr.unice.polytech.steats.rating.RatingLevel;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CampusUser extends User {
    private Cart cart;

    public CampusUser() {
        super();
        this.cart = new Cart(new HashMap<>());
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart(new HashMap<>());
    }


    public Cart getCart() {
        return this.cart;
    }

    public void rateRestaurantByUser(RatingSystem ratingSystem, UUID restaurantID, RatingLevel ratingLevel) {
        ratingSystem.rateRestaurant(restaurantID, ratingLevel);
    }

    public void rateDeliveryPersonByUser(RatingSystem ratingSystem, UUID deliveryID, RatingLevel ratingLevel) {
        ratingSystem.rateDeliveryPerson(deliveryID, ratingLevel);
    }
     public Map<UUID,RatingLevel> getRestaurantRatings(RatingSystem ratingSystem){
        return ratingSystem.getRestaurantRatings();
     }

     public Map<UUID,RatingLevel> getDeliveryPersonRatings(RatingSystem ratingSystem){
        return ratingSystem.getDeliveryPersonRatings();
     }
}
