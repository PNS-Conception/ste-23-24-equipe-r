package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    private List<Restaurant> restaurants;
    private Restaurant restaurant;
    private CampusUser orderOwner;
    private List<Order> orders;
    private LocalDateTime deliveryTime;
    private DeliveryLocation deliveryLocation;

    protected OrderDetails(CampusUser campusUser, List<Order> orders, LocalDateTime deliveryTime, DeliveryLocation deliveryLocation, List<Restaurant> restaurants, Restaurant restaurant) {
        this.orderOwner = campusUser;
        this.orders = orders;
        this.deliveryTime = deliveryTime;
        this.deliveryLocation = deliveryLocation;
        this.restaurants = restaurants;
        this.restaurant = restaurant;
    }

    public CampusUser getOrderOwner() {
        return this.orderOwner;
    }

    public List<Order> getOrders() {
        return this.orders;
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
    public Map<Menu, Integer> menusOrdered(){
        return this.orderOwner.getCart().getMenusCopy();
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
}