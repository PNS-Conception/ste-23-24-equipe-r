package fr.unice.polytech.steats.rating;

import fr.unice.polytech.steats.users.User;

public class RatingInfo {

    private User user;
    private Double rate;

    public RatingInfo(User user, Double rate) {
        this.user = user;
        this.rate = rate;
    }

    public User getUserFromRatingInfo() {
        return user;
    }

    public Double getRateFromRatingInfo() {
        return rate;
    }


}
