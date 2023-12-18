package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Schedule;

import java.time.LocalDateTime;
import java.util.Map;

public interface TimeslotHandler {
    void calculateTimeslot(Schedule schedule, LocalDateTime deliveryTime, Map<Menu, Integer> menusOrdered)
            throws DeliveryDateNotAvailable, EmptyCartException;
}
