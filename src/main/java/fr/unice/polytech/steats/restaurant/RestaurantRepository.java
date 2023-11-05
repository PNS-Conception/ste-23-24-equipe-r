package fr.unice.polytech.steats.restaurant;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.shared.BasicRepositoryImpl;
import java.util.*;

public class RestaurantRepository extends BasicRepositoryImpl<Restaurant, UUID> {
    /*public Restaurant getRestaurantByOrder(Order order){
        Iterable<Restaurant> iterable = this.findAll();
        List<Restaurant> restaurants = new ArrayList<>();
        iterable.forEach(restaurants::add);
        for (Restaurant restaurant : restaurants){
            if (restaurant.getPendingOrders().contains(order)){
                return restaurant;
            }
        }
        throw new NoSuchElementException();
    }*/

    public boolean checkRequirements(Restaurant restaurant){
        for(Restaurant r:this.findAll()){
            if(r.getRestaurantName().equals(restaurant.getRestaurantName())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void save(Restaurant restaurant, UUID id){
        if(checkRequirements(restaurant)){
            super.save(restaurant,restaurant.getId());
        }
    }

    public Restaurant getRestaurantByName(String name) {
        for (Restaurant restaurant : this.findAll()) {
            if (restaurant.getRestaurantName().equals(name)) {
                return restaurant;
            }
        }
        return null;
    }

}
