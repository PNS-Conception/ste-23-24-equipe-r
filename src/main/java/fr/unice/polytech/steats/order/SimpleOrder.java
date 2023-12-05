
package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import java.util.*;

public class SimpleOrder extends Order {
    private DeliveryLocation deliveryLocation;
    private double discount = 0.1;
    private Map<Menu, Integer> menusOrdered;
    private OrderDetails orderDetails;

    public SimpleOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
        this.menusOrdered = orderDetails.menusOrdered();
        OrderVolume.getInstance().addOrder((this));
    }

    public Restaurant getRestaurant() {
        return orderDetails.getRestaurant();
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
        this.orderPublisher.notifySubscribers(this);
    }
    public DeliveryLocation getDeliveryLocation(){
        return orderDetails.getDeliveryLocation();
    }

    public int getTotalMenus(){
        int sum = 0;
        for (int value : menusOrdered.values()) {
            sum += value;
        }
        return sum;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public double getPrice(){
        double total = 0;
        for (Map.Entry<Menu, Integer> entry : menusOrdered.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            total += menu.getBasePrice() * quantity;
        }
        if(getTotalMenus()>=10){
            return total-(total*discount);
        }
        return total;
    }
    public Menu getMenu(){
        return menusOrdered.keySet().iterator().next();
    }

    
    

}