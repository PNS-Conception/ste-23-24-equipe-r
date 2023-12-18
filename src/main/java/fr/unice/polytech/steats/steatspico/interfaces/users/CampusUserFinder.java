package fr.unice.polytech.steats.steatspico.interfaces.users;

import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.util.Optional;

public interface CampusUserFinder {
    Optional<CampusUser> findByName(String name);
}
