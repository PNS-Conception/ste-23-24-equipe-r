package fr.unice.polytech.steats.cucumber.stats;
import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.stats.StatisticsManager;
import fr.unice.polytech.steats.users.User;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetStatisticalInsights {

    User user;

    Order order;


    @Given("an Order has been created but not {string}")
    public void an_Order_has_been_created_but_not(String status) {
        order= new Order();
        assertNotEquals(order.getStatus().toString(), status);
    }
    @When("a {string} retrieves statistical data on order volumes")
    public void a_retrieves_statistical_data_on_order_volumes_for_the_current_time_period(String name) {
        user = new User(name);
    }
    @Then("the Order is included in the order volume data")
    public void the_Order_is_included_in_the_order_volume_data_for_the_current_period() throws NoOrdersPlacedException {
        StatisticsManager statisticsManager = new StatisticsManager();
        assertTrue(statisticsManager.getOrderVolumesOverTime().contains(order));
    }
}
