package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.enumeration.OrderStatus;
import fr.unice.polytech.steats.model.CampusUser;
import fr.unice.polytech.steats.model.Order;
import fr.unice.polytech.steats.service.OrderDao;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckDeliveryStatus {

    Order order;
    CampusUser customer;
    OrderDao orderRegistry = new OrderDao();
    OrderStatus orderStatus;

    @Given("a {string} have an order with an order Id {string}")
    public void a_have_an_order_with_an_order_id(String customerName, String orderID) {
        customer = new CampusUser(customerName);
        order = new Order(orderID, customer);
        orderRegistry.save(order);
    }

    @When("{string} checks the delivery status of the order")
    public void he_user_checks_the_delivery_status_of_the(String string) {
        orderStatus = orderRegistry.getOrderStatus(order.getOrderID(), customer);
    }

    @Then("he should get the delivery status of the order")
    public void he_should_get_the_delivery_status_of_the_order() {
        assert orderStatus == order.getOrderStatus();
    }


    @Given("a {string} who doesn't have an order with an order Id {string}")
    public void a_who_doesn_t_have_an_order_with_an_order_id(String customerName, String orderID) {
        customer = new CampusUser(customerName);
        CampusUser customer2 = new CampusUser();
        order = new Order(orderID, customer2);
        orderRegistry.save(order);
    }

    @Then("he can't get the delivery status of the order")
    public void heCanTGetTheDeliveryStatusOfTheOrder() {
        assert orderStatus == null;
    }
}
