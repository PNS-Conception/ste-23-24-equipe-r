package fr.unice.polytech.steats.cart;

import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.*;

public class Cart {
    private Map<Restaurant, Map<Menu, Integer>> restaurantMenusMap;

    public Cart() {
        this.restaurantMenusMap = new HashMap<>();
    }

    public Map<Restaurant, Map<Menu, Integer>> getRestaurantMenusMap() {
        return restaurantMenusMap;
    }

    public void addMenu(Restaurant restaurant, Menu menu, int quantity) {
        restaurantMenusMap.computeIfAbsent(restaurant, k -> new HashMap<>())
                .merge(menu, quantity, Integer::sum);
    }

    public int getSize() {
        return restaurantMenusMap.values().stream()
                .flatMapToInt(menus -> menus.values().stream().mapToInt(i -> i))
                .sum();
    }

    public void emptyCart() {
        restaurantMenusMap.clear();
    }

    public Map<Restaurant, Map<Menu, Integer>> getMenusCopy() {
        Map<Restaurant, Map<Menu, Integer>> deepCopy = new HashMap<>();
        for (Map.Entry<Restaurant, Map<Menu, Integer>> restaurantEntry : restaurantMenusMap.entrySet()) {
            Map<Menu, Integer> menuCopy = new HashMap<>();
            for (Map.Entry<Menu, Integer> menuEntry : restaurantEntry.getValue().entrySet()) {
                Menu copiedMenu = new Menu(menuEntry.getKey());
                menuCopy.put(copiedMenu, menuEntry.getValue());
            }
            deepCopy.put(restaurantEntry.getKey(), menuCopy);
        }
        return deepCopy;
    }
}
