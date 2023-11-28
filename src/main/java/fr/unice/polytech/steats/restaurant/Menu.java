package fr.unice.polytech.steats.restaurant;


import fr.unice.polytech.steats.users.CampusUserStatus;

import java.util.Objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.UUID;

public class Menu {
    private final UUID id;
    private final String menuName;
    private final double basePrice;

    private Map<CampusUserStatus, Double> campusUserStatusPrice;


    private Map<String, List<String>> options = new HashMap<>();
    private Map<String, String> selectedOptions = new HashMap<>();

    public Menu(String menuName, double basePrice) {
        this.id = UUID.randomUUID();
        this.menuName = menuName;
        this.basePrice = basePrice;
        this.campusUserStatusPrice = new HashMap<>();
    }
    public Menu(Menu menu){
        this.id = UUID.randomUUID();
        this.menuName = menu.getMenuName();
        this.basePrice = menu.getBasePrice();
        this.campusUserStatusPrice = menu.getCampusUserStatusPrice();
        this.options = menu.options;
        this.selectedOptions = menu.selectedOptions;
    }

// --Commented out by Inspection START (28/11/2023 22:36):
//    // Getters and Setters
//    public UUID getMenuID() {
//        return id;
//    }
// --Commented out by Inspection STOP (28/11/2023 22:36)

    public String getMenuName() {
        return menuName;
    }

    public double getBasePrice() {
        return basePrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(menu.basePrice, basePrice) == 0 && menuName.equals(menu.menuName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(menuName, basePrice);
    }
    public Map<String, List<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, List<String>> options) {
        this.options = options;
    }

// --Commented out by Inspection START (28/11/2023 22:36):
//    public Map<String, String> getSelectedOptions() {
//        return selectedOptions;
//    }
// --Commented out by Inspection STOP (28/11/2023 22:36)

    public void setSelectedOptions(Map<String, String> selectedOptions) {
        this.selectedOptions = selectedOptions;

    }

    public Map<CampusUserStatus, Double> getCampusUserStatusPrice() {
        return campusUserStatusPrice;
    }

    public void setPriceForUserType(CampusUserStatus campusUserStatus, double price){
        this.campusUserStatusPrice.put(campusUserStatus, price);
    }

// --Commented out by Inspection START (28/11/2023 22:36):
//    public double getPriceForUserType(CampusUserStatus campusUserStatus){
//        return this.campusUserStatusPrice.get(campusUserStatus);
//    }
// --Commented out by Inspection STOP (28/11/2023 22:36)

    public void setCampusUserStatusPrice(Map<CampusUserStatus, Double> campusUserStatusPrice) {
        this.campusUserStatusPrice = campusUserStatusPrice;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
