package fr.unice.polytech.steats.restaurant;
import fr.unice.polytech.steats.order.Order;
import net.bytebuddy.asm.Advice;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String restaurantName;
    private List<Menu> menus = new ArrayList<>();
    private List<Order> pendingOrders = new ArrayList<>();
    private Schedule schedule;

    public Restaurant(String restaurantName, LocalTime openingTime, LocalTime closingTime,int slotCapacity) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        this.schedule = new Schedule(openingTime, closingTime, slotCapacity);
    }
    public Restaurant(String restaurantName){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);  // 9:00 AM
        LocalTime closingTime = LocalTime.of(20, 0);  // 5:00 PM
        int capacity = 10;
        this.schedule = new Schedule(openingTime, closingTime, capacity);
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

    public Schedule getSchedule() {
        return this.schedule;
    }

}
