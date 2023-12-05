package fr.unice.polytech.steats.restaurant;

import fr.unice.polytech.steats.exceptions.restaurant.AlreadyExistingRestaurantException;

import java.time.LocalTime;

public interface RestaurantRegistration {
    void register(String restaurantName, LocalTime openingTime, LocalTime closingTime, int capacity)
            throws AlreadyExistingRestaurantException;
}
