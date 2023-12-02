package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.notification.delivery.DeliverySubscriber;

public class DeliveryPerson extends User implements DeliverySubscriber {
    String phoneNumber;


    public DeliveryPerson(String deliveryPersonName) {
        super(deliveryPersonName,UserRole.DELIVERY_PERSON);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(Delivery delivery) {
        if(delivery.getDeliveryPerson().equals(this))
            System.out.println("You have a new delivery to do");
    }
}
