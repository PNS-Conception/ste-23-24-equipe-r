package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String restaurantName;
    private List<Menu> menus;

    private List<RestaurantStaff> employees;
    private List<Order> orderList;

    public Restaurant(String restaurantID, String restaurantName) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.orderList = new ArrayList<>();  // Initialize the order list
        this.employees = new ArrayList<>();  // Initialize the employees list
        this.menus = new ArrayList<>();  // Initialize the menus list
    }

    public Restaurant(String restaurantName) {
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

    public List<RestaurantStaff> getEmployees() {
        return employees;
    }

    public void addEmployee(RestaurantStaff employee) {
        employees.add(employee);
    }

    public List<Order> getOrderList() {
        if (orderList.isEmpty()) {
            throw new IllegalStateException("No order found for acceptance");
        }
        return orderList;
    }
    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
