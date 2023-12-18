package fr.unice.polytech.steats.steatspico.entities.order;

import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;

public class BuffetOrder extends Order{

    private DeliveryLocation deliveryLocation;
    private Menu buffetUnit;
    private OrderDetails orderDetails;
    private int numberOfParticipants;

    public BuffetOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
        this.numberOfParticipants = orderDetails.getRecipientNumber();
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
        return buffetUnit;
    }

    public void setBuffetUnit(Menu buffetUnit) {
        this.buffetUnit = buffetUnit;
    }
    public double getPrice(){
        return buffetUnit.getBasePrice()*numberOfParticipants;
    }
}
