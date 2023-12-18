package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.users.User;

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
