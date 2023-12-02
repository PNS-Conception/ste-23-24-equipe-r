package fr.unice.polytech.steats.restaurant;

import fr.unice.polytech.steats.exceptions.restaurant.AlreadyExistingRestaurantException;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class RestaurantRegistry {
    private final RestaurantRepository restaurantRepository;

    public RestaurantRegistry(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    public void register(String restaurantName, LocalTime openingTime, LocalTime closingTime, int capacity)
            throws AlreadyExistingRestaurantException{
        if (findByName(restaurantName).isPresent()){
            throw new AlreadyExistingRestaurantException(restaurantName);
        }
        Schedule schedule = new Schedule(openingTime, closingTime, capacity);
        Restaurant restaurant = new Restaurant(restaurantName, schedule);
        restaurantRepository.save(restaurant, restaurant.getId());
    }
    public Optional<Restaurant> findByName(String name){
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(),false)
                .filter(restaurant -> name.equals(restaurant.getRestaurantName())).findAny();
    }
}
