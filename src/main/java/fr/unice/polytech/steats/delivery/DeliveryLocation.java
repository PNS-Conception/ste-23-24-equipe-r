package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeliveryLocation {

    public static final DeliveryLocation LIBRARY = new DeliveryLocation("Library", 43.6187102, 7.075759);
    public static final DeliveryLocation STUDENT_CENTER = new DeliveryLocation("Student Center", 43.617439, 7.078827);
    public static final DeliveryLocation ENGINEERING_BUILDING = new DeliveryLocation("Engineering Building", 43.615849, 7.080506);
    public static final DeliveryLocation SPORTS_COMPLEX = new DeliveryLocation("Sports Complex", 43.614250, 7.077740);
    public static final DeliveryLocation RESIDENCE_HALL = new DeliveryLocation("Residence Hall", 43.616790, 7.072890);

    private String locationName;
    private double latitude;
    private double longitude;


    private DeliveryLocation(String locationName, double latitude, double longitude) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    private static final Map<String, DeliveryLocation> LOCATIONS_MAP = createLocationsMap();

    private static Map<String, DeliveryLocation> createLocationsMap() {
        Map<String, DeliveryLocation> map = new HashMap<>();
        map.put("Library", LIBRARY);
        map.put("Student Center", STUDENT_CENTER);
        map.put("Engineering Building", ENGINEERING_BUILDING);
        map.put("Sports Complex", SPORTS_COMPLEX);
        map.put("Residence Hall", RESIDENCE_HALL);
        return map;
    }

    public static DeliveryLocation getByName(String name) {
        return LOCATIONS_MAP.get(name);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryLocation deliveryLocation = (DeliveryLocation) o;
        return ((DeliveryLocation) o).locationName.equals(locationName);
    }
}
