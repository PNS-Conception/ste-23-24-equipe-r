package fr.unice.polytech.steats.exceptions.restaurant;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeliveryDateNotAvailable extends Exception{
    public DeliveryDateNotAvailable(LocalDateTime date){
        super("Delivery date : "+date+" is not available");
    }
}
