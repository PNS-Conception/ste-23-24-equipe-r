package fr.unice.polytech.steats.restaurant;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Menu {
    private UUID id;
    private String menuName;
    private double price;

    private Map<String, List<String>> options = new HashMap<>();
    private Map<String, String> selectedOptions = new HashMap<>();

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


    public Map<String, List<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, List<String>> options) {
        this.options = options;
    }

    public Map<String, String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(Map<String, String> selectedOptions) {
        this.selectedOptions = selectedOptions;

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
