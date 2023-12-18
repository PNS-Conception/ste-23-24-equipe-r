package fr.unice.polytech.steats.steatspico.interfaces.restaurant;

import fr.unice.polytech.steats.steatspico.exceptions.restaurant.AlreadyExistingRestaurantException;

import java.time.LocalTime;

public interface RestaurantRegistration {
    void register(String restaurantName, LocalTime openingTime, LocalTime closingTime, int capacity)
            throws AlreadyExistingRestaurantException;
}
