package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.delivery.DeliverySubscriber;

import java.util.Map;

public class DeliveryPerson extends User implements DeliverySubscriber {
    int phoneNumber;
    public DeliveryPerson(String deliveryPersonName, UserRole userRole) {
        super(deliveryPersonName,userRole);
    }

    @Override
    public void updateDelivery(Map<String, Object> event) {
        System.out.println("updating the Delivery Person about the delivery : ");
        System.out.println(event);
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
