package fr.unice.polytech.steats.restaurant;
import fr.unice.polytech.steats.exceptions.NonExistentOrder;
import fr.unice.polytech.steats.exceptions.NonExistentTimeSlot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String restaurantName;
    private List<Menu> menus = new ArrayList<>();
    private Schedule schedule;

    public Restaurant(String restaurantName, Schedule schedule) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        this.schedule = schedule;
    }
    public Restaurant(String restaurantName){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
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
    public Schedule getSchedule() {
        return this.schedule;
    }
    public int getTimeSlotCapacity(TimeSlot timeslot) throws NonExistentTimeSlot {
        for (TimeSlot tslot : schedule.getTimeSlots()){
            if (timeslot.equals(tslot)){
                return tslot.getCapacity();
            }
        }
        throw new NonExistentTimeSlot(timeslot);
    }

}
