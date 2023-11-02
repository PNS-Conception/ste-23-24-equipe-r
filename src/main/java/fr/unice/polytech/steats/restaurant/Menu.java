package fr.unice.polytech.steats.restaurant;

import java.util.Objects;
import java.util.UUID;

public class Menu {
    private UUID id;
    private String menuName;
    private double price;

    public Menu(String menuName, double price) {
        this.id = UUID.randomUUID();
        this.menuName = menuName;
        this.price = price;
    }
    public Menu(Menu menu){
        this.id = UUID.randomUUID();
        this.menuName = menu.getMenuName();
        this.price = menu.getPrice();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(menu.price, price) == 0 && menuName.equals(menu.menuName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(menuName, price);
    }
}
