package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.steatspico.repositories.UserRepository;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.interfaces.users.CampusUserFinder;
import fr.unice.polytech.steats.steatspico.interfaces.users.CampusUserRegistration;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CampusUserRegistry implements CampusUserFinder, CampusUserRegistration {
    final UserRepository userRepository;

    public CampusUserRegistry(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void register(String name) throws AlreadyExistingUserException {
        if (findByName(name).isPresent()){
            throw new AlreadyExistingUserException(name);
        }
        CampusUser campusUser = new CampusUser(name);
        userRepository.save(campusUser, campusUser.getId());
    }


    @Override
    public Optional<CampusUser> findByName(String name) {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(campusUser -> name.equals(campusUser.getName()))
                .map(campusUser -> (CampusUser) campusUser) // Cast to CampusUser
                .findAny();
    }




}
