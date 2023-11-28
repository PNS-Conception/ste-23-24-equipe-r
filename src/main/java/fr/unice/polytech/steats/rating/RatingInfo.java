package fr.unice.polytech.steats.rating;

import fr.unice.polytech.steats.users.User;

public class RatingInfo {

    private final User user;
    private final Double rate;

    public RatingInfo(User user, Double rate) {
        this.user = user;
        this.rate = rate;
    }



    public Double getRateFromRatingInfo() {
        return rate;
    }


}
