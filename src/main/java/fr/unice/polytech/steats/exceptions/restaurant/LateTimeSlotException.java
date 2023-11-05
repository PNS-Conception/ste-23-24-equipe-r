package fr.unice.polytech.steats.exceptions.restaurant;

public class LateTimeSlotException extends Exception{
    public LateTimeSlotException(){
        super("cannot put order in this timeslot anymore : too late");
    }
}
