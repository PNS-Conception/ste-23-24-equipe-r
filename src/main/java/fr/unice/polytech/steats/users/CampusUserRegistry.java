package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CampusUserRegistry {
    final UserRepository userRepository;

    public CampusUserRegistry(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void register(String name) throws AlreadyExistingUserException {
        if (findByName(name).isPresent()){
            throw new AlreadyExistingUserException(name);
        }
        CampusUser campusUser = new CampusUser(name);
        userRepository.save(campusUser, campusUser.getId());
    }


    public Optional<CampusUser> findByName(String name) {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(campusUser -> name.equals(campusUser.getName()))
                .map(campusUser -> (CampusUser) campusUser) // Cast to CampusUser
                .findAny();
    }




}
