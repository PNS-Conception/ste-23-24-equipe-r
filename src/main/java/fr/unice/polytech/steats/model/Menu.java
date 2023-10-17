package fr.unice.polytech.steats.model;

public class Menu {
    private String menuID;
    private String menuName;
    private double price;

    public Menu(String menuID, String menuName) {
        this.menuID = menuID;
        this.menuName = menuName;
    }

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    // Getters and Setters
    public String getMenuID() {
        return menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getPrice() {
        return price;
    }
}
