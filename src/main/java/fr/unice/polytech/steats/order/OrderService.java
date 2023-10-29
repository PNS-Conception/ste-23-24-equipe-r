package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;

    // constructor, other methods...

    public List<Order> getOrdersForRestaurant(UUID restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return orderRepository.findOrdersByRestaurant(restaurant);
        } else {
            // Restaurant not found, handle accordingly
        }
    }
}