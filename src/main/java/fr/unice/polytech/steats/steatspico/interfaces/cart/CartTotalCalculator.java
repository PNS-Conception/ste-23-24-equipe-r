package fr.unice.polytech.steats.steatspico.interfaces.cart;

import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

public interface CartTotalCalculator {
    double getPriceForUser(CampusUser campusUser);
}
