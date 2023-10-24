package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.enumeration.OrderStatus;
import fr.unice.polytech.steats.model.DeliveryPerson;
import fr.unice.polytech.steats.model.Order;
import fr.unice.polytech.steats.repository.OrderDao;
import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class PickUpAnOrderSteps {

    OrderDao orderDao = new OrderDao();

    Order order = new Order(10.0);
    DeliveryPerson deliveryPerson;


    // Scenario: No Order Ready for delivery
    @Given("Order had the status {string}")
    public void has_the_status(String string2) {
        order = new Order(10.0);
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.toString().equals(string2)) {
                order.setOrderStatus(orderStatus);
            }
        }
        assertEquals(order.getOrderStatus().toString(), string2);
    }

    @When("the {string} consult the list of ready orders for delivery")
    public void the_consult_the_list_of_ready_orders_for_delivery(String string) {
        deliveryPerson = new DeliveryPerson(string);
    }
    @Then("the list should contains {string} order")
    public void the_should_contains_order(String integer) {
        assertEquals(orderDao.getAll().size(), Integer.parseInt(integer));
    }


    // Scenario: Order Ready for delivery
    @Given("Order had a status {string}")
    public void has_a_status(String string2) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.toString().equals(string2)) {
                order.setOrderStatus(orderStatus);
            }
        }
        assertEquals(order.getOrderStatus().toString(), string2);
    }


    @Then("the list should contains the order")
    public void the_should_contains_at_least_order() {
        List<Order> orders = orderDao.getAll();
        orders.add(order);
        assertTrue(orders.contains(order));
    }

}
