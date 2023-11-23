package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.UUID;

public class UserDeliveryNotification extends Notification{

    UUID deliveryPersonId ;
    String deliveryPersonPhoneNumber;




    public UserDeliveryNotification(Delivery delivery) {
        super();
        this.deliveryPersonId = delivery.getDeliveryPerson().getId();
        this.deliveryPersonPhoneNumber = delivery.getDeliveryPerson().getPhoneNumber();
        this.setUserId(delivery.getOrder().getCustomer().getId());
    }

    @Override
    public String toString() {
        return "UserDeliveryNotification{" +
                "deliveryPersonId=" + deliveryPersonId +
                ", deliveryPersonPhoneNumber='" + deliveryPersonPhoneNumber + '\'' +
                '}';
    }
}
