package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.users.CampusUserRegistration;
import fr.unice.polytech.steats.users.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class CampusUserSteps {
    private final UserRepository userRepository;
    private final CampusUserRegistration campusUserRegistration;

    public CampusUserSteps(FacadeContainer container){
        this.userRepository = container.userRepository;
        this.campusUserRegistration = container.campusUserRegistry;
    }
    @Before
    public void setUp(){
        userRepository.deleteAll();
    }
    @Given("{string} is a campus user")
    public void isACampusUser(String username) throws AlreadyExistingUserException {
        campusUserRegistration.register(username);
    }
}
