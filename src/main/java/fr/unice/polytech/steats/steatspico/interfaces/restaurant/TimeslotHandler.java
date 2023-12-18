package fr.unice.polytech.steats.steatspico.interfaces.restaurant;

import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Schedule;

import java.time.LocalDateTime;
import java.util.Map;

public interface TimeslotHandler {
    void calculateTimeslot(Schedule schedule, LocalDateTime deliveryTime, Map<Menu, Integer> menusOrdered)
            throws DeliveryDateNotAvailable, EmptyCartException;
}
