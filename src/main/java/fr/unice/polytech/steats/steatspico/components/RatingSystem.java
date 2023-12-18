package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.User;
import fr.unice.polytech.steats.steatspico.entities.users.UserRole;

import java.util.*;

public class RatingSystem {
    private final Map<Restaurant, List<RatingInfo>> restaurantRatings;
    private final Map<User, List<RatingInfo>> deliveryPersonRatings;

    public RatingSystem(){
        restaurantRatings= new HashMap<>();
        deliveryPersonRatings= new HashMap<>();
    }

    ///// getters
    public Map<Restaurant, List<RatingInfo>> getRestaurantRatings() {
        return restaurantRatings;
    }
    public Map<User, List<RatingInfo>> getDeliveryPersonRatings() {
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


    public Double averageRatingDeliveryPerson(User user) {
        return calculateAverageRating(deliveryPersonRatings.get(user));
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

    public void rateDeliveryPerson(User userVoted, User user, Double rate) {
        if(userVoted.getUserRole().equals(UserRole.DELIVERY_PERSON)){
            addRating(deliveryPersonRatings, userVoted, user, rate);
            return;
        }
        throw new IllegalArgumentException("User voted is not a delivery person");

    }

}

