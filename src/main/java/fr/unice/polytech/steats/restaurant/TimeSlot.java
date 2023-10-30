package fr.unice.polytech.steats.restaurant;

import java.time.LocalTime;

public class TimeSlot {
    private LocalTime startTime;
    private int capacity;

    public TimeSlot(LocalTime startTime, int capacity) {
        this.startTime = startTime;
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void substractCapacity(){
        this.capacity--;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }
}