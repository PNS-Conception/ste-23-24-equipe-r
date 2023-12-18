package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.Map;

public class BuffetOrder extends Order{

    private DeliveryLocation deliveryLocation;
    private Menu buffetUnit;
    private OrderDetails orderDetails;
    private int numberOfParticipants;

    public BuffetOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
        this.numberOfParticipants = orderDetails.getRecipientNumber();
        this.buffetUnit = ;
    }
    public Restaurant getRestaurant() {
        return orderDetails.getRestaurant();
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
        this.orderPublisher.notifySubscribers(this);
    }
    public void setNumberOfParticipants(int n){
        this.numberOfParticipants = n;
    }
    public DeliveryLocation getDeliveryLocation(){
        return orderDetails.getDeliveryLocation();
    }
    public Menu getMenu(){
        return menusOrdered.keySet().iterator().next();
    }
}
