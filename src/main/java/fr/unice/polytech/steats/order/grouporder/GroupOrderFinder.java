package fr.unice.polytech.steats.order.grouporder;

import java.util.Optional;

public interface GroupOrderFinder {
    Optional<GroupOrder> findByCode(String code);
}
