package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Schedule;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.AlreadyExistingRestaurantException;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantRegistration;
import fr.unice.polytech.steats.steatspico.repositories.RestaurantRepository;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class RestaurantRegistry implements RestaurantRegistration, RestaurantLocator {
    private final RestaurantRepository restaurantRepository;

    public RestaurantRegistry(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public void register(String restaurantName, LocalTime openingTime, LocalTime closingTime, int capacity)
            throws AlreadyExistingRestaurantException{
        if (findByName(restaurantName).isPresent()){
            throw new AlreadyExistingRestaurantException(restaurantName);
        }
        Schedule schedule = new Schedule(openingTime, closingTime, capacity);
        Restaurant restaurant = new Restaurant(restaurantName, schedule);
        restaurantRepository.save(restaurant, restaurant.getId());
    }
    @Override
    public Optional<Restaurant> findByName(String name){
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(),false)
                .filter(restaurant -> name.equals(restaurant.getRestaurantName())).findAny();
    }
}
