package fr.unice.polytech.steats.steatspico.interfaces.users;

import fr.unice.polytech.steats.steatspico.exceptions.user.AlreadyExistingUserException;

public interface CampusUserRegistration {
    void register(String name) throws AlreadyExistingUserException;
}
