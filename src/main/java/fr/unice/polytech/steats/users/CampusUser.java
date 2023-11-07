package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.exceptions.order.InsufficientBalanceException;
import fr.unice.polytech.steats.rating.RatingSystem;

import java.util.*;

public class CampusUser extends User {
    private Cart cart;
    private double balance;

    public CampusUser() {
        super();
        this.cart = new Cart();
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart();
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

     public void setBalance(double balance) {
         this.balance = balance;
     }

    public double getBalance() {
        return balance;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void pay(double amount) throws InsufficientBalanceException {
        if(this.balance < amount){
            throw new InsufficientBalanceException();
        }
        this.balance -= amount;
    }
}
