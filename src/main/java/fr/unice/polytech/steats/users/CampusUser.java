package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.*;

public class CampusUser extends User {
    private Cart cart;
    private double balance;

    private CampusUserStatus status;

    private List<Order> PreviousOrders ;

    public CampusUser() {
        super();
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name, CampusUserStatus status) {
        super(name);
        this.cart = new Cart();
        this.status = status;
    }


    public Cart getCart() {
        return this.cart;
    }



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

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public void addMenuToCart(Menu mn){
        this.cart.addMenu(mn);
    }

    public CampusUserStatus getStatus() {
        return status;
    }

    public void setStatus(CampusUserStatus status) {
        this.status = status;
    }
}
