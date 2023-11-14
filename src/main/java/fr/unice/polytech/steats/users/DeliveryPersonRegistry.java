package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static fr.unice.polytech.steats.users.UserRole.DELIVERY_PERSON;

public class DeliveryPersonRegistry {
    UserRepository userRepository;
    public DeliveryPersonRegistry(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User deliveryPerson) {
        if (findByName(deliveryPerson.getName()).isPresent()){
            return;
        }
        userRepository.save(deliveryPerson, deliveryPerson.getId());
    }

    public Optional<User> findByName(String name) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(name)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
