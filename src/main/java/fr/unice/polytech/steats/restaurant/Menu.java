package fr.unice.polytech.steats.restaurant;


import fr.unice.polytech.steats.users.CampusUserStatus;

import java.util.Objects;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class Menu {
    private final UUID id;
    private final String menuName;
    private final double basePrice;
    private double OptionsPrice=0;


    private Map<CampusUserStatus, Double> campusUserStatusPrice;


    private Map<String, Map<String,Integer>> options = new HashMap<>();
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

    public String getMenuName() {
        return menuName;
    }

    public double getBasePrice() {
        return basePrice;
    }
    public Map<String, Map<String,Integer>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Map<String,Integer>> options) {
        this.options = options;
    }

    public void setSelectedOptions(Map<String, String>selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public Map<String, String> getSelectedOptions() {
        return selectedOptions;
    }

    public Map<CampusUserStatus, Double> getCampusUserStatusPrice() {
        return campusUserStatusPrice;
    }

    public void setPriceForUserType(CampusUserStatus campusUserStatus, double price){
        this.campusUserStatusPrice.put(campusUserStatus, price);
    }

    public void setCampusUserStatusPrice(Map<CampusUserStatus, Double> campusUserStatusPrice) {
        this.campusUserStatusPrice = campusUserStatusPrice;
    }

    public double getOptionsPrice() {
        return OptionsPrice;
    }

    public void setOptionsPrice(double optionsPrice) {
        OptionsPrice = optionsPrice;
    }

    public double calculateNewPrice(){
        double OptionsPrice=0;
        for (String option : selectedOptions.keySet())
            OptionsPrice+=options.get(option).get(selectedOptions.get(option));
        return OptionsPrice;
    }

    public UUID getId() {
        return id;
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
}
