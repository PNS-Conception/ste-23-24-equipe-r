package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class CampusUserSteps {
    private final UserRepository userRepository;
    private final CampusUserRegistry campusUserRegistry;

    public CampusUserSteps(FacadeContainer container){
        this.userRepository = container.userRepository;
        this.campusUserRegistry = container.campusUserRegistry;
    }
    @Before
    public void setUp(){
        userRepository.deleteAll();
    }
    @Given("{string} is a campus user")
    public void isACampusUser(String username) throws AlreadyExistingUserException {
        campusUserRegistry.register(username);
    }
}
