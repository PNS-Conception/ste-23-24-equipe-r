package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.users.CampusUser;

public interface Bank {
    boolean executePayment(CampusUser user, double amount);
}
