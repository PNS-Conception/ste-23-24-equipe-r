package fr.unice.polytech.steats.delivery;

public class TimeSlot {
    private String time;

    public TimeSlot(String timeSlot) {
        this.time = timeSlot;
    }

    public String getTimeSlot() {
        return time;
    }

    public void setTimeSlot(String timeSlot) {
        this.time = timeSlot;
    }
}