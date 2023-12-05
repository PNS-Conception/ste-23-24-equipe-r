package fr.unice.polytech.steats.users;

import java.util.Optional;

public interface CampusUserFinder {
    Optional<CampusUser> findByName(String name);
}
