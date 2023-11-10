package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertTrue;
public class AccessPreviousOrders {
    CampusUser CU ;
    List<Order> PreviousOrders = new ArrayList<>();

    @Given("a logged-in Campus user {string} and a list of previous orders")
    public void a_logged_in_Campus_user_and_a_list_of_previous_orders (String name) {
        PreviousOrders.add(new Order(new Restaurant("R1"),new Menu("MaxBurger",12), LocalDate.of(2023, 11, 11)));
        PreviousOrders.add(new Order(new Restaurant("R1"),new Menu("CheeseBurger",13), LocalDate.of(2023, 12, 19)));
        PreviousOrders.add(new Order(new Restaurant("R1"),new Menu("DoubleBurger",17), LocalDate.of(2024, 1, 12)));
        CU = new CampusUser(name);



    }
    @Given("a logged-in Campus user {string}")
    public void a_logged_in_campus_user(String name) {
        CU = new CampusUser(name);



    }

    @When("the campus user Karim clicks on show previous orders")
    public void the_campus_user_karim_clicks_on_show_previous_orders() {
        CU.setPreviousOrders(PreviousOrders);


    }

    @Then("a list of all his previous orders in reverse chronological order")
    public void a_list_of_all_his_previous_orders_in_reverse_chronological_order() {
        for (int i = CU.getPreviousOrders().size() - 1; i >= 0; i--) {
            Order o = CU.getPreviousOrders().get(i);
            System.out.println(o.getRestaurant().getRestaurantName()+" "+o.getOrderDate()+" "+o.getMenu());
        }
    }

    @When("there is no previous orders")
    public void there_is_no_previous_orders() {

        assertTrue(CU.getPreviousOrders().isEmpty());
    }
    @Then("Karim should see a message saying {string}")
    public void karim_should_see_a_message_saying(String msg) {
        System.out.println(msg);
    }
}
