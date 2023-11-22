package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.order.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPerson extends User {
    String phoneNumber;


    public DeliveryPerson(String deliveryPersonName, UserRole userRole) {
        super(deliveryPersonName,userRole);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
