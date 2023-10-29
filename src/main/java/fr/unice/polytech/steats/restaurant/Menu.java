package fr.unice.polytech.steats.restaurant;

import java.util.UUID;

public class Menu {
    private UUID id;
    private String menuName;
    private double price;

    public Menu(String menuName, double price) {
        this.menuName = menuName;
        this.price = price;
    }

    // Getters and Setters
    public UUID getMenuID() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getPrice() {
        return price;
    }
}
