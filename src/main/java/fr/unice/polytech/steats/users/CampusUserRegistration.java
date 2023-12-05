package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;

public interface CampusUserRegistration {
    void register(String name) throws AlreadyExistingUserException;
}
