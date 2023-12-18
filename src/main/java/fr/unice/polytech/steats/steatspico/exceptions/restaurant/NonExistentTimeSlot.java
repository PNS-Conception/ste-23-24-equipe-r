package fr.unice.polytech.steats.steatspico.exceptions.restaurant;

import fr.unice.polytech.steats.steatspico.entities.restaurant.TimeSlot;

public class NonExistentTimeSlot extends Exception {
    public NonExistentTimeSlot(TimeSlot timeslot){
        super("timeslot : "+timeslot.getStartTime()+" is not available");
    }
}
