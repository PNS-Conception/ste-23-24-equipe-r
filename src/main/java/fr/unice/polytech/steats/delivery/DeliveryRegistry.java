package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.users.DeliveryPerson;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class DeliveryRegistry {
    DeliveryRepository deliveryRepository;

    public DeliveryRegistry(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void register(Order order) {
        Delivery delivery = new Delivery(order);
        deliveryRepository.save(delivery, delivery.getId());
    }

    public DeliveryRepository getDeliveryRepository() {
        return deliveryRepository;
    }

    public void markDeliveryAsInProgress(Delivery delivery, DeliveryPerson deliveryPerson){
        delivery.setDeliveryPerson(deliveryPerson);
        delivery.setStatus(IN_PROGRESS);
    }
}