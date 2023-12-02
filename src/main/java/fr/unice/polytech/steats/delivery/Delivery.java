package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.notification.*;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.READY;

public class Delivery {
    Order order;
    DeliveryPerson deliveryPerson;
    UUID id;
    DeliveryStatus status;
    private List<Subscriber> subscribers;



    public Delivery(SimpleOrder order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
        subscribers = new ArrayList<>();
    }

    public void setReady(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        setStatus(READY);
        notifySubscribers();
    }

    public UUID getId() {
        return id;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }



    public void subscribe(Subscriber subscriber) {
        if(subscribers==null){
            subscribers = new ArrayList<>();
        }
        this.subscribers.add(subscriber);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    private Map<String,Object> getEventForDeliveryPerson(){
        Map<String,Object> event = new HashMap<>();
        event.put("pickup time", order.getDeliveryTime());
        event.put("restaurants names", order.getRestaurants());
        event.put("delivery location", order.getDeliveryLocation());
        event.put("customer name", order.getCustomer().getName());
        return event;
    }

    private Map<String,Object> getEventForCustomer(){
        Map<String,Object> event = new HashMap<>();
        event.put("order id", order.getId());
        event.put("delivery date", order.getDeliveryTime());
        event.put("delivery location", order.getDeliveryLocation());
        event.put("delivery person phone number",deliveryPerson.getPhoneNumber());
        return event;
    }


    public void notifySubscribers() {

        Notification deliveryNotification = new Notification(getEventForDeliveryPerson(), Collections.singletonList(deliveryPerson));
        Notification userNotification = new Notification(getEventForCustomer(), Collections.singletonList(order.getCustomer()));


        for(Subscriber subscriber : subscribers){
            subscriber.update(deliveryNotification);
            subscriber.update(userNotification);
        }
    }


}
