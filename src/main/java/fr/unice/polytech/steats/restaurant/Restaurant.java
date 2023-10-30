package fr.unice.polytech.steats.restaurant;

import fr.unice.polytech.steats.users.RestaurantStaff;
import fr.unice.polytech.steats.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private UUID id;
    private String restaurantName;
    private List<Menu> menus = new ArrayList<>();
    private List<Order> pendingOrders = new ArrayList<>();

    public Restaurant(String restaurantName) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
    }
    public UUID getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<Menu> getMenus() {
        return menus;
    }
    public List<Order> getPendingOrders() {
        return pendingOrders;
    }


    public Menu getMenufromName(String menuName) {
        for (Menu menu : menus) {
            if (menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        return null;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
    public void addOrder(Order order){pendingOrders.add(order);}
}
