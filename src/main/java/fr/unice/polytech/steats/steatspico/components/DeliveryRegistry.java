package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.repositories.DeliveryRepository;
import fr.unice.polytech.steats.steatspico.entities.users.DeliveryPerson;

import static fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryStatus.IN_PROGRESS;

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
