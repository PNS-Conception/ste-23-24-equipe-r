package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderSubscriber;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;
import fr.unice.polytech.steats.users.User;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class Delivery {
    Order order;
    DeliveryPerson deliveryPerson;
    UUID id;
    DeliveryStatus status;

    private final List<DeliverySubscriber> subscribers = new ArrayList<>();



    public Delivery(Order order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
        subscribe(order.getCustomer());
    }

    public UUID getId() {
        return id;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        status = IN_PROGRESS;
        subscribe(deliveryPerson);
        notifySubscribers();
    }

    public User getDeliveryPerson() {
        return deliveryPerson;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
    public void subscribe(DeliverySubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(OrderSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (DeliverySubscriber sb :
                subscribers) {
            Map<String, Object> event = new HashMap<>();
            if(sb instanceof DeliveryPerson){
                event.put("pickUpTime",order.getTimeSlot());
                event.put("restaurant",order.getRestaurant().getRestaurantName());
                event.put("deliveryLocation",order.getDeliveryLocation());
                event.put("username",order.getCustomer().getName());
            }
            if(sb instanceof CampusUser){
                event.put("deliveryPersonId",deliveryPerson.getId());
                event.put("phoneNumber",deliveryPerson.getPhoneNumber());
            }
            sb.updateDelivery(event);
        }
    }


}
