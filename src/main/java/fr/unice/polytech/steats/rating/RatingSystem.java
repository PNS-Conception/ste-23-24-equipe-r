package fr.unice.polytech.steats.rating;

import java.util.*;

public class RatingSystem {
    private Map<UUID, List<Double>> restaurantRatings;
    private Map<UUID, List<Double>> deliveryPersonRatings;

    public RatingSystem(){
        restaurantRatings= new HashMap<>();
        deliveryPersonRatings= new HashMap<>();
    }

    ///// getters
    public Map<UUID, List<Double>> getRestaurantRatings() {
        return restaurantRatings;
    }
    public Map<UUID, List<Double>> getDeliveryPersonRatings() {
        return deliveryPersonRatings;
    }



    public Double averageRating(UUID id) {
        double somme = 0.0;
        if (restaurantRatings.containsKey(id)) {
            for (Double rating : restaurantRatings.get(id)) {
                somme += rating;
            }
            double average = somme / restaurantRatings.get(id).size();

            String formattedResult = String.format("%.1f", average);
            formattedResult = formattedResult.replace(',', '.');
            return Double.parseDouble(formattedResult);
        }
        if(deliveryPersonRatings.containsKey(id)){
            for (Double rating : deliveryPersonRatings.get(id)) {
                somme += rating;
            }
            double average = somme / deliveryPersonRatings.get(id).size();

            String formattedResult = String.format("%.1f", average);
            formattedResult = formattedResult.replace(',', '.');
            return Double.parseDouble(formattedResult);
        }
        else {
            return somme;
        }

    }


    public void rateRestaurant(UUID restaurantID, Double rate) {
        if (restaurantRatings.containsKey(restaurantID)) {
            restaurantRatings.get(restaurantID).add(rate);
        }
        else {
             List<Double> listOfRatingOfNewRestaurant = new ArrayList<>();
             listOfRatingOfNewRestaurant.add(rate);
             restaurantRatings.put(restaurantID, listOfRatingOfNewRestaurant);
        }
    }


    public void rateDeliveryPerson(UUID deliveryId, Double rate) {
        if (deliveryPersonRatings.containsKey(deliveryId)) {
            deliveryPersonRatings.get(deliveryId).add(rate);
        }
        else {
            List<Double> listOfRatingOfNewDeliveryPerson = new ArrayList<>();
            listOfRatingOfNewDeliveryPerson.add(rate);
            deliveryPersonRatings.put(deliveryId, listOfRatingOfNewDeliveryPerson);
        }
    }
}

