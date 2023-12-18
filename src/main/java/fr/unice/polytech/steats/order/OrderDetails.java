package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    private Restaurant restaurant;
    private List<Restaurant> restaurants;
    private CampusUser orderOwner;
    private LocalDateTime deliveryTime;
    private DeliveryLocation deliveryLocation;
    private int recipientNumber;

    protected OrderDetails(CampusUser campusUser, LocalDateTime deliveryTime, DeliveryLocation deliveryLocation, List<Restaurant> restaurants, Restaurant restaurant, int recipientNumber) {
        this.orderOwner = campusUser;
        this.deliveryTime = deliveryTime;
        this.deliveryLocation = deliveryLocation;
        this.restaurants = restaurants;
        this.restaurant = restaurant;
        this.recipientNumber = recipientNumber;
    }

    public CampusUser getOrderOwner() {
        return this.orderOwner;
    }

    public LocalDateTime getDeliveryTime() {
        return this.deliveryTime;
    }

    public DeliveryLocation getDeliveryLocation() {
        return this.deliveryLocation;
    }
    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }
    public Map<Restaurant, Map<Menu, Integer>> menusOrdered(){
        return this.orderOwner.getCart().getMenusCopy();
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
    public int getRecipientNumber(){return this.recipientNumber;}
}