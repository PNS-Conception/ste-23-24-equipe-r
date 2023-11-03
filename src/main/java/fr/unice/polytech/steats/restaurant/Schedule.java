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

    public Schedule(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        initializeTimeSlots(0);
    }

    public void setCapacity(int slotCapacity) {
        if(timeSlots.size() > 0) {
            timeSlots.clear();
        }
        initializeTimeSlots(slotCapacity);
    }

    private void initializeTimeSlots(int slotCapacity) {
        for (LocalTime time = openingTime; time.isBefore(closingTime);
             time = time.plusMinutes(SLOT_DURATION_IN_MINUTES)) {
            timeSlots.add(new TimeSlot(time, slotCapacity));
        }
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time);
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
