package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import java.time.LocalDateTime;
import java.util.List;
public class OrderDetailsBuilder {
    private CampusUser orderOwner;
    private List<Order> orders;
    private LocalDateTime deliveryTime;
    private DeliveryLocation deliveryLocation;
    private List<Restaurant> restaurants;
    private Restaurant restaurant;
    public OrderDetails build(){
        return new OrderDetails(orderOwner, orders, deliveryTime, deliveryLocation, restaurants, restaurant);
    }

    public OrderDetailsBuilder orderOwner(CampusUser orderOwner){
        this.orderOwner = orderOwner;
        return this;
    }
    public OrderDetailsBuilder orders(List<Order> orders){
        this.orders = orders;
        return this;
    }
    public OrderDetailsBuilder deliveryTime(LocalDateTime deliveryTime){
        this.deliveryTime = deliveryTime;
        return this;
    }
    public OrderDetailsBuilder deliveryLocation(DeliveryLocation deliveryLocation){
        this.deliveryLocation = deliveryLocation;
        return this;
    }
    public OrderDetailsBuilder restaurants(List<Restaurant> restaurants){
        this.restaurants = restaurants;
        return this;
    }
    public OrderDetailsBuilder restaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        return this;
    }
}