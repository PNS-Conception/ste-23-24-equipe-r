package fr.unice.polytech.steats.exceptions.restaurant;

import fr.unice.polytech.steats.restaurant.Timeslot;

public class NonExistentTimeSlot extends Exception {
    public NonExistentTimeSlot(Timeslot timeslot){
        super("timeslot : "+timeslot.getStartTime()+" is not available");
    }
}
