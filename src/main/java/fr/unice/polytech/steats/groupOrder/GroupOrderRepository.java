package fr.unice.polytech.steats.groupOrder;

import fr.unice.polytech.steats.shared.BasicRepositoryImpl;

import java.util.Optional;
import java.util.UUID;

public class GroupOrderRepository extends BasicRepositoryImpl<GroupOrder, UUID> {
    public Optional<GroupOrder> findByID(UUID id){
        return this.findById(id);
    }
}

