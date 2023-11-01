package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.order.Cart;
import fr.unice.polytech.steats.rating.RatingLevel;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.*;

public class CampusUser extends User {
    private Cart cart;

    public CampusUser() {
        super();
        this.cart = new Cart(new ArrayList<>());
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart(new ArrayList<>());
    }


    public Cart getCart() {
        return this.cart;
    }

    public void addMenuToCart(Menu menu) {
        cart.addToCart(menu);
    }

    public void removeFromCart(Menu menu) {cart.removeFromCart(menu);}

    public void rateRestaurantByUser(RatingSystem ratingSystem, UUID restaurantID, int rating) {
        if(rating < 0 || rating > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        ratingSystem.rateRestaurant(restaurantID, (double)rating);
    }

    public void rateDeliveryPersonByUser(RatingSystem ratingSystem, UUID deliveryID, int rating) {
        if(rating< 0 || rating > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        ratingSystem.rateDeliveryPerson(deliveryID,(double) rating);
    }
     public Map<UUID, List<Double>> getRestaurantRatings(RatingSystem ratingSystem){
        return ratingSystem.getRestaurantRatings();
     }

     public Map<UUID,List<Double>> getDeliveryPersonRatings(RatingSystem ratingSystem){
        return ratingSystem.getDeliveryPersonRatings();
     }
}
