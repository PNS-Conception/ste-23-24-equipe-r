package fr.unice.polytech.steats.delivery;

public class DeliveryLocation {
    private String locationName;

    public DeliveryLocation(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}