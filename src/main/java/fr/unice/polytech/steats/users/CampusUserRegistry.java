package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CampusUserRegistry {
    CampusUserRepository campusUserRepository;
    public CampusUserRegistry(CampusUserRepository campusUserRepository) {
        this.campusUserRepository = campusUserRepository;
    }
    public void register(String name) throws AlreadyExistingUserException {
        if (findByName(name).isPresent()){
            throw new AlreadyExistingUserException(name);
        }
        CampusUser campusUser = new CampusUser(name);
        campusUserRepository.save(campusUser, campusUser.getId());
    }

    public Optional<CampusUser> findByName(String name) {
        return StreamSupport.stream(campusUserRepository.findAll().spliterator(), false)
                .filter(campusUser -> name.equals(campusUser.getName())).findAny();
    }

}
