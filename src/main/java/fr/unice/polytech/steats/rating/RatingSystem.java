package fr.unice.polytech.steats.rating;

import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.User;

import java.util.*;

public class RatingSystem {
    private Map<Restaurant, List<RatingInfo>> restaurantRatings;
    private Map<UUID, List<RatingInfo>> deliveryPersonRatings;

    public RatingSystem(){
        restaurantRatings= new HashMap<>();
        deliveryPersonRatings= new HashMap<>();
    }

    ///// getters
    public Map<Restaurant, List<RatingInfo>> getRestaurantRatings() {
        return restaurantRatings;
    }
    public Map<UUID, List<RatingInfo>> getDeliveryPersonRatings() {
        return deliveryPersonRatings;
    }


    public Double averageRatingRestaurant(Restaurant restaurant){
        double somme = 0.0;
        if (restaurantRatings.containsKey(restaurant)) {
            for (RatingInfo ratingInfo : restaurantRatings.get(restaurant)) {
                double rating = ratingInfo.getRateFromRatingInfo();
                somme += rating;
            }
            double average = somme / restaurantRatings.get(restaurant).size();

            String formattedResult = String.format("%.1f", average);
            formattedResult = formattedResult.replace(',', '.');
            return Double.parseDouble(formattedResult);
        }
        return somme;

    }


    public Double averageRatingDeliveryPerson(UUID id) {
        double somme = 0.0;
        if(deliveryPersonRatings.containsKey(id)){
            for (RatingInfo ratingInfo : deliveryPersonRatings.get(id)) {
                double rating = ratingInfo.getRateFromRatingInfo();
                somme += rating;
            }
            double average = somme / deliveryPersonRatings.get(id).size();

            String formattedResult = String.format("%.1f", average);
            formattedResult = formattedResult.replace(',', '.');
            return Double.parseDouble(formattedResult);
        }
        return somme;

    }


    public void rateRestaurant(Restaurant restaurant,User user, Double rate) {
        if(rate< 0 || rate > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        if (restaurantRatings.containsKey(restaurant)) {
            RatingInfo ratingInfo = new RatingInfo(user, rate);
            restaurantRatings.get(restaurant).add(ratingInfo);
        }
        else {
            List<RatingInfo> listOfRatingInfoOfNewRestaurant = new ArrayList<>();
            RatingInfo rateInfo = new RatingInfo(user, rate);
             listOfRatingInfoOfNewRestaurant.add(rateInfo);
             restaurantRatings.put(restaurant, listOfRatingInfoOfNewRestaurant);
        }
    }


    public void rateDeliveryPerson(UUID deliveryId,User user, Double rate) {
        if(rate< 0 || rate > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        if (deliveryPersonRatings.containsKey(deliveryId)) {
            RatingInfo ratingInfo = new RatingInfo(user, rate);
            deliveryPersonRatings.get(deliveryId).add(ratingInfo);
        }
        else {
            List<RatingInfo> listOfRatingInfoOfNewDeliveryPerson = new ArrayList<>();
            RatingInfo rateInfo = new RatingInfo(user, rate);
            listOfRatingInfoOfNewDeliveryPerson.add(rateInfo);
            deliveryPersonRatings.put(deliveryId, listOfRatingInfoOfNewDeliveryPerson);
        }
    }
}

