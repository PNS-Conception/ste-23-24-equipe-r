package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RestaurantRegistry implements Dao<Restaurant>{
    List<Restaurant> restaurants;

    public RestaurantRegistry(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public RestaurantRegistry() {
        restaurants = new ArrayList<>();
    }

    public List<Restaurant> getAll() {
        return restaurants;
    }

    public void save(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void update(Restaurant restaurant, String[] params) {

    }

    public void delete(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public Optional<Restaurant> get(String id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(id)) {
                return Optional.of(restaurant);
            }
        }
        return Optional.empty();
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
