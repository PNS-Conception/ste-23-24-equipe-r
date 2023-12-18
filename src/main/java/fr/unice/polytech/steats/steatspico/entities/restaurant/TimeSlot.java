package fr.unice.polytech.steats.steatspico.entities.restaurant;
import java.time.LocalDateTime;
import java.util.Objects;

public class TimeSlot {
    private final LocalDateTime startTime;
    private int capacity;

    public TimeSlot(LocalDateTime startTime, int capacity) {
        this.startTime = startTime;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void subtractCapacity(int numberOfMenus) {
        if(this.capacity>=numberOfMenus){
            this.capacity-=numberOfMenus;
        }
        else throw new IllegalArgumentException("Not enough capacity");
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
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
}
