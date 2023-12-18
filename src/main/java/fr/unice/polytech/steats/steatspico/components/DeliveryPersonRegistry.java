package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.repositories.UserRepository;
import fr.unice.polytech.steats.steatspico.entities.users.User;

import java.util.Optional;


public class DeliveryPersonRegistry {
    final UserRepository userRepository;
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
