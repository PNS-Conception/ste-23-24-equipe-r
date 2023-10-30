package fr.unice.polytech.steats.restaurant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static final int SLOT_DURATION_IN_MINUTES = 10;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public Schedule(LocalTime openingTime, LocalTime closingTime, int slotCapacity) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        initializeTimeSlots(slotCapacity);
    }

    private void initializeTimeSlots(int slotCapacity) {
        for (LocalTime time = openingTime; time.isBefore(closingTime);
             time = time.plusMinutes(SLOT_DURATION_IN_MINUTES)) {
            timeSlots.add(new TimeSlot(time, slotCapacity));
        }
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public TimeSlot findTimeSlotByStartTime(LocalTime startTime) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getStartTime().equals(startTime)) {
                return timeSlot;
            }
        }
        return null;
    }
}
