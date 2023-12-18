package fr.unice.polytech.steats.steatspico.exceptions.order;

public class NoOrdersPlacedException extends Exception{
    public NoOrdersPlacedException(){
        super("No orders have been placed yet");
    }
}
