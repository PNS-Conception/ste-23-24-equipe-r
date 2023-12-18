package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.entities.order.GroupOrder;

import java.util.Optional;

public interface GroupOrderFinder {
    Optional<GroupOrder> findByCode(String code);
}
