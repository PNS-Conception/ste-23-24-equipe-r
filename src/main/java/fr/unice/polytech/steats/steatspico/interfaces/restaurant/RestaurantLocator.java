package fr.unice.polytech.steats.steatspico.interfaces.restaurant;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;

import java.util.Optional;

public interface RestaurantLocator {
    Optional<Restaurant> findByName(String name);
}
