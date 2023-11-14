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

    private Double calculateAverageRating(List<RatingInfo> ratings) {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (RatingInfo rating : ratings) {
            total += rating.getRateFromRatingInfo();
        }

        double average = total / ratings.size();
        String formattedResult = String.format("%.1f", average).replace(',', '.');
        return Double.parseDouble(formattedResult);
    }


    public Double averageRatingRestaurant(Restaurant restaurant){
        return calculateAverageRating(restaurantRatings.get(restaurant));
    }


    public Double averageRatingDeliveryPerson(UUID id) {
        return calculateAverageRating(deliveryPersonRatings.get(id));
    }

    private <T> void addRating(Map<T, List<RatingInfo>> ratingsMap, T entity, User user, Double rate) {
        if (rate < 0 || rate > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        List<RatingInfo> ratings = ratingsMap.computeIfAbsent(entity, k -> new ArrayList<>());
        ratings.add(new RatingInfo(user, rate));
    }

    public void rateRestaurant(Restaurant restaurant, User user, Double rate) {
        addRating(restaurantRatings, restaurant, user, rate);
    }

    public void rateDeliveryPerson(UUID deliveryId, User user, Double rate) {
        addRating(deliveryPersonRatings, deliveryId, user, rate);
    }

}

