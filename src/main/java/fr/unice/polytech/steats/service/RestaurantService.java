package fr.unice.polytech.steats.service;

import fr.unice.polytech.steats.model.Menu;
import fr.unice.polytech.steats.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantService {
    private List<Restaurant> restaurantList;

    public RestaurantService() {
        Restaurant restaurant1 = new Restaurant("1", "Restaurant A");
        Restaurant restaurant2 = new Restaurant("2", "Restaurant B");

        restaurant1.addMenu(new Menu("1", "Menu A1"));
        restaurant2.addMenu(new Menu("2", "Menu B1"));

        this.restaurantList = Arrays.asList(restaurant1, restaurant2);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantList;
    }
}
