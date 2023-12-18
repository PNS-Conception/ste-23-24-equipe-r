package fr.unice.polytech.steats.steatspico.entities.order;

import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailsBuilder {

    private CampusUser orderOwner;
    private LocalDateTime deliveryTime;
    private DeliveryLocation deliveryLocation;
    private List<Restaurant> restaurants;
    private Restaurant restaurant;
    private int recipientNumber;

    public OrderDetails build(){
        return new OrderDetails(orderOwner, deliveryTime, deliveryLocation, restaurants, restaurant, recipientNumber);
    }

    public OrderDetailsBuilder orderOwner(CampusUser orderOwner){
        this.orderOwner = orderOwner;
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
    public OrderDetailsBuilder recipientNumber(int n){
        this.recipientNumber = n;
        return this;
    }

}
