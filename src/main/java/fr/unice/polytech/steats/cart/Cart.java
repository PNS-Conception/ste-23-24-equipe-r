package fr.unice.polytech.steats.cart;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Menu, Integer> menuMap;
    private Restaurant restaurant;

    public Cart() {
        Map<Menu, Integer> menuMap1 = new HashMap<>();
        this.menuMap=menuMap1;
    }
    public Cart(Map<Menu, Integer> menusMap, Restaurant restaurant) {
        this.menuMap = menusMap;
        this.restaurant = restaurant;
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
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void addMenu(Menu menu){
        this.menuMap.put(menu,1);
    }
}
