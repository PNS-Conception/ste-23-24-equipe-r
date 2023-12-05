package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;

public interface GroupOrderRegistration {
    GroupOrder register(CampusUser campusUser, LocalDateTime deliveryTime, DeliveryLocation deliveryLocation);
}
