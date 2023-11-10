package fr.unice.polytech.steats.restaurant;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.users.CampusUserStatus;
import fr.unice.polytech.steats.util.DailyResetScheduler;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Restaurant {
    private UUID id;
    private String restaurantName;
    private List<Menu> menus = new ArrayList<>();
    private Schedule schedule;
    private DailyResetScheduler dailyResetScheduler;


    private RestaurantStatus restaurantStatus;
    public Restaurant(String restaurantName, Schedule schedule) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        this.schedule = schedule;
        this.dailyResetScheduler = new DailyResetScheduler(schedule);
        this.restaurantStatus= RestaurantStatus.NOT_ELIGIBLE_FOR_DISCOUNT;
    }
    public Restaurant(String restaurantName){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
        int capacity = 10;
        this.schedule = new Schedule(openingTime, closingTime, capacity);
        this.dailyResetScheduler = new DailyResetScheduler(schedule);
        this.restaurantStatus= RestaurantStatus.NOT_ELIGIBLE_FOR_DISCOUNT;
    }
    public Restaurant(String restaurantName, Schedule schedule, RestaurantStatus restaurantStatus) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        this.schedule = schedule;
        this.dailyResetScheduler = new DailyResetScheduler(schedule);
        this.restaurantStatus= restaurantStatus;
    }

    public Restaurant(String restaurantName, RestaurantStatus restaurantStatus){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
        int capacity = 10;
        this.schedule = new Schedule(openingTime, closingTime, capacity);
        this.dailyResetScheduler = new DailyResetScheduler(schedule);
        this.restaurantStatus= restaurantStatus;

    }

    public UUID getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<Menu> getMenus() {
        if(getRestaurantStatus()==RestaurantStatus.ELIGIBLE_FOR_DISCOUNT){
            return menus;
        }else {
            for(Menu menu : menus){
                menu.setCampusUserStatusPrice(new HashMap<>());
            }
        }
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

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public RestaurantStatus getRestaurantStatus(){
        return restaurantStatus;
    }

    public void setRestaurantStatus(RestaurantStatus restaurantStatus){
        this.restaurantStatus=restaurantStatus;
    }




}
