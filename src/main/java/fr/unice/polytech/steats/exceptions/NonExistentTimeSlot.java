package fr.unice.polytech.steats.exceptions;

import fr.unice.polytech.steats.restaurant.TimeSlot;

public class NonExistentTimeSlot extends Exception {
    public NonExistentTimeSlot(TimeSlot timeslot){
        super("timeslot : "+timeslot.getStartTime()+" is not available");
    }
}
