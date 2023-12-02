package fr.unice.polytech.steats.restaurant;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Schedule {
    public static final int SLOT_DURATION_IN_MINUTES = 10;
    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private final List<TimeSlot> timeslots = new ArrayList<>();
    private int slotCapacity;

    public Schedule(LocalTime openingTime, LocalTime closingTime, int slotCapacity) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.slotCapacity = slotCapacity;
    }
    public void setCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }

    public List<TimeSlot> getTimeSlots() {
        return timeslots;
    }

    public Optional<TimeSlot> findTimeSlotByStartTime(LocalDateTime startTime) {
        return timeslots.stream()
                .filter(slot -> slot.getStartTime().equals(startTime))
                .findFirst();
    }
    public void addTimeslot(TimeSlot timeslot) {
        timeslots.add(timeslot);
    }

    public int getMaxCapacity() {
        return slotCapacity;
    }

    public LocalTime getClosingTime() {
        return this.closingTime;
    }
}