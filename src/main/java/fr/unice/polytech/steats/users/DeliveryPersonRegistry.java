package fr.unice.polytech.steats.users;

import java.util.Optional;


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
