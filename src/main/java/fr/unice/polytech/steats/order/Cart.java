package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.restaurant.Menu;
import java.util.Map;

public class Cart {
    private Map<Menu, Integer> menuMap;
    public Cart(Map<Menu, Integer> menusMap) {
        this.menuMap = menusMap;
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
    public double getPrice(){
        double total = 0;
        for (Map.Entry<Menu, Integer> entry : menuMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            total += menu.getPrice() * quantity;
        }
        return total;
    }
    public void emptyCart() {
        menuMap.clear();
    }
}
