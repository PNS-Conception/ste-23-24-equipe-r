package fr.unice.polytech.steats.steatspico.entities.restaurant;

import fr.unice.polytech.steats.steatspico.exceptions.restaurant.NonExistentMenuException;
import fr.unice.polytech.steats.steatspico.entities.notification.Notification;
import fr.unice.polytech.steats.steatspico.components.NotificationRegistry;
import fr.unice.polytech.steats.steatspico.interfaces.users.Recipient;
import fr.unice.polytech.steats.steatspico.interfaces.users.PickupTimeSubscriber;
import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.time.LocalTime;
import java.util.*;

public class Restaurant implements PickupTimeSubscriber, Recipient {

    NotificationRegistry notificationRegistry = NotificationRegistry.getInstance();
    private final UUID id;
    private final String restaurantName;
    private final List<Menu> menus = new ArrayList<>();
    private Schedule schedule;
    private final RestaurantStatus restaurantStatus;
    public Restaurant(String restaurantName, Schedule schedule) {
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        this.schedule = schedule;
        this.restaurantStatus= RestaurantStatus.NOT_ELIGIBLE_FOR_DISCOUNT;
    }
    public Restaurant(String restaurantName){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
        int capacity = 10;
        this.schedule = new Schedule(openingTime, closingTime, capacity);
        this.restaurantStatus= RestaurantStatus.NOT_ELIGIBLE_FOR_DISCOUNT;
    }


    public Restaurant(String restaurantName, RestaurantStatus restaurantStatus){
        this.id = UUID.randomUUID();
        this.restaurantName = restaurantName;
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
        int capacity = 10;
        this.schedule = new Schedule(openingTime, closingTime, capacity);
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


    public Menu getMenufromName(String menuName) throws NonExistentMenuException {
        for (Menu menu : menus) {
            if (menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        throw new NonExistentMenuException(menuName);
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
    public Schedule getSchedule() {
        return this.schedule;
    }



    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public RestaurantStatus getRestaurantStatus(){
        return restaurantStatus;
    }

    @Override
    public void update(Order order) {
        Map<String,Object> event = new HashMap<>();
        event.put("Order Id", order.getId());
        event.put("Pick Up Time ", order.getDeliveryTime().minusHours(2));
        Notification notification = new Notification(event,this);
        notificationRegistry.add(notification);
    }
}
