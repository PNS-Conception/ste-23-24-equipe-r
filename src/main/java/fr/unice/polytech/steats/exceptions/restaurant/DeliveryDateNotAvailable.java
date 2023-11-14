package fr.unice.polytech.steats.exceptions.restaurant;

import java.time.LocalTime;

public class DeliveryDateNotAvailable extends Exception{
    public DeliveryDateNotAvailable(LocalTime date){
        super("Delivery date : "+date+" is not available");
    }
}
