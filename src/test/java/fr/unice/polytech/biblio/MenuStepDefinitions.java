package fr.unice.polytech.biblio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MenuStepDefinitions {

    CampusUser user = new CampusUser();

    HashMap<String,String> menuHashMap = new HashMap<String,String>();


    @Given("{string} is on the menu page")
    public void is_on_the_menu_page(String string) {
        //TODO
        user.setName(string);
    }

    @When("{string} as a time slot is chosen")
    public void isChosen(String timeSlot)  {
        user.setTimeSlot(timeSlot);
    }

    @Then("{string} is displayed")
    public void isDisplayed(String menuName) {

        menuHashMap.put("2023-09-26 10:00:00", "Breakfast Menu");
        menuHashMap.put("2023-09-26 12:00:00", "Lunch Menu");
        menuHashMap.put("2023-09-26 19:00:00", "Dinner Menu");

        assertTrue(menuHashMap.containsKey(user.getTimeSlot()));
        assertEquals(menuName, menuHashMap.get(user.getTimeSlot()));
    }


}
