package fr.unice.polytech.steats.exceptions.order;

import java.util.UUID;

public class NonExistentOrder extends Exception {
    public NonExistentOrder(UUID id){
        super("order ID : "+id+" does not exist");
    }
}
