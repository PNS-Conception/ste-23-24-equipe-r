package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.users.DeliveryPerson;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class DeliveryRegistry {
    final DeliveryRepository deliveryRepository;

    public DeliveryRegistry(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void register(Order simpleOrder) {
        Delivery delivery = new Delivery(simpleOrder);
        deliveryRepository.save(delivery, delivery.getId());
    }

    public void assign(Delivery delivery, DeliveryPerson deliveryPerson) {
        delivery.setDeliveryPerson(deliveryPerson);
        delivery.setStatus(IN_PROGRESS);
        delivery.deliveryPublisher.subscribe(deliveryPerson);
        deliveryRepository.save(delivery, delivery.getId());
    }

    public DeliveryRepository getDeliveryRepository() {
        return deliveryRepository;
    }


}
