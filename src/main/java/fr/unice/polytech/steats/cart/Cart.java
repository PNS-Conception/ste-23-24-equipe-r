package fr.unice.polytech.steats.cart;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Menu, Integer> menuMap = new HashMap<>();
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
    public Map<Menu, Integer> getMenusCopy() {
        Map<Menu, Integer> deepCopy = new HashMap<>();
        for (Map.Entry<Menu, Integer> entry : menuMap.entrySet()) {
            Menu originalMenu = entry.getKey();
            int quantity = entry.getValue();
            Menu copiedMenu = new Menu(originalMenu);
            deepCopy.put(copiedMenu, quantity);
        }
        return deepCopy;
    }
}