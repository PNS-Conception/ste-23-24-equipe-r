package fr.unice.polytech.steats.restaurant;

import java.util.Optional;

public interface RestaurantLocator {
    Optional<Restaurant> findByName(String name);
}
