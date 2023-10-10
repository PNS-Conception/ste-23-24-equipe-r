package fr.unice.polytech.steats.model;

import java.util.List;

public class RestaurantRegistry {
    List<Restaurant> restaurants;
    public RestaurantRegistry(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurantFromName(String restaurantName) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                return restaurant;
            }
        }
        return null;
    }
}
