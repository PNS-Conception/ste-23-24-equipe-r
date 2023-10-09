package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String restaurantName;
    private List<Menu> menus;

    public Restaurant(String restaurantID, String restaurantName) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.menus = new ArrayList<>();  // Initialize the menus list
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
}
