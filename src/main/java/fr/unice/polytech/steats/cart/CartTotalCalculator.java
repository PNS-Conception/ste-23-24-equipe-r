package fr.unice.polytech.steats.cart;

import fr.unice.polytech.steats.users.CampusUser;

public interface CartTotalCalculator {
    double getPriceForUser(CampusUser campusUser);
}
