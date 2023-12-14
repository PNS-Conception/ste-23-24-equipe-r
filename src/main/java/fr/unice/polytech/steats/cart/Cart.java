package fr.unice.polytech.steats.cart;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Menu, Integer> menuMap;
    private Restaurant restaurant;

    public Cart() {
        this.menuMap= new HashMap<>();
    }

    public Map<Menu, Integer> getMenuMap() {
        return menuMap;
    }
    public int getSize(){
        int totalQuantity = 0;
        for (int quantity : menuMap.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }
    public void emptyCart() {
        menuMap.clear();
    }

    public void addMenu(Menu menu){
        this.menuMap.put(menu,1);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
