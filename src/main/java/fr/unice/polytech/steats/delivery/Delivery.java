package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.notification.*;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.READY;

public class Delivery {
    final SimpleOrder simpleOrder;
    DeliveryPerson deliveryPerson;
    final UUID id;
    DeliveryStatus status;
    private List<Subscriber> subscribers;



    public Delivery(SimpleOrder simpleOrder) {
        id = UUID.randomUUID();
        this.simpleOrder = simpleOrder;
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

// --Commented out by Inspection START (28/11/2023 22:24):
//    public SimpleOrder getOrder() {
//        return simpleOrder;
//    }
// --Commented out by Inspection STOP (28/11/2023 22:24)



    public void subscribe(Subscriber subscriber) {
        if(subscribers==null){
            subscribers = new ArrayList<>();
        }
        this.subscribers.add(subscriber);
    }

// --Commented out by Inspection START (28/11/2023 22:24):
//    public List<Subscriber> getSubscribers() {
//        return subscribers;
//    }
// --Commented out by Inspection STOP (28/11/2023 22:24)

    private Map<String,Object> getEventForDeliveryPerson(){
        Map<String,Object> event = new HashMap<>();
        event.put("pickup time",simpleOrder.getDeliveryTime());
        event.put("restaurant name",simpleOrder.getRestaurant().getRestaurantName());
        event.put("delivery location",simpleOrder.getDeliveryLocation());
        event.put("customer name",simpleOrder.getCustomer().getName());
        return event;
    }

    private Map<String,Object> getEventForCustomer(){
        Map<String,Object> event = new HashMap<>();
        event.put("order id",simpleOrder.getId());
        event.put("delivery date",simpleOrder.getDeliveryTime());
        event.put("delivery location",simpleOrder.getDeliveryLocation());
        event.put("delivery person phone number",deliveryPerson.getPhoneNumber());
        return event;
    }


    public void notifySubscribers() {

        Notification deliveryNotification = new Notification(getEventForDeliveryPerson(), Collections.singletonList(deliveryPerson));
        Notification userNotification = new Notification(getEventForCustomer(), Collections.singletonList(simpleOrder.getCustomer()));


        for(Subscriber subscriber : subscribers){
            subscriber.update(deliveryNotification);
            subscriber.update(userNotification);
        }
    }


}
