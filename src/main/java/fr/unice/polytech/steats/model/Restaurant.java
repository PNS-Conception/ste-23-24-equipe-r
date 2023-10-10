package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String restaurantName;
    private List<Menu> menus;

    private List<RestaurantStaff> employees;
    private List<Menu> orderList;

    public Restaurant(String restaurantID, String restaurantName) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.orderList = new ArrayList<>();  // Initialize the order list
        this.employees = new ArrayList<>();  // Initialize the employees list
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

    public List<RestaurantStaff> getEmployees() {
        return employees;
    }

    public void addEmployee(RestaurantStaff employee) {
        employees.add(employee);
    }

    public List<Menu> getOrderList() {
        if (orderList.isEmpty()) {
            throw new IllegalStateException("No order found for acceptance");
        }
        return orderList;
    }

    public void setOrderList(List<Menu> orderList) {
        this.orderList = orderList;
    }
}
