package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.exceptions.user.AlreadyExistingUserException;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.CampusUserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class CampusUserSteps {
    private final CampusUserRepository campusUserRepository;
    private final CampusUserRegistry campusUserRegistry;

    public CampusUserSteps(FacadeContainer container){
        this.campusUserRepository = container.campusUserRepository;
        this.campusUserRegistry = container.campusUserRegistry;
    }
    @Before
    public void setUp(){
        campusUserRepository.deleteAll();
    }
    @Given("{string} is a campus user")
    public void isACampusUser(String username) throws AlreadyExistingUserException {
        campusUserRegistry.register(username);
    }
}
