package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.order.Subscriber;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CampusUserRegistry {
    UserRepository userRepository;

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
