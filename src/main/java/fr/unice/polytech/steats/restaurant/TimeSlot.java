package fr.unice.polytech.steats.restaurant;
import java.time.LocalTime;
import java.util.Objects;

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

    public void subtractCapacity() {
        this.capacity--;
    }

    public void subtractCapacity(int numberOfMenus) {
        this.capacity-=numberOfMenus;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(startTime, timeSlot.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, capacity);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "startTime=" + startTime +
                ", capacity=" + capacity +
                '}';
    }
}
