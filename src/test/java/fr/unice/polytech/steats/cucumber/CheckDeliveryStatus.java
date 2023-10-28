package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.user.CampusUser;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDao;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckDeliveryStatus {

    Order order;
    CampusUser customer;
    OrderDao orderRegistry = new OrderDao();
    OrderStatus orderStatus;

    @Given("a {string} have an order")
    public void a_have_an_order_with_an_order_id(String customerName) {
        customer = new CampusUser(customerName);
        order = new Order(customer);
        orderRegistry.save(order);
    }
    @When("{string} checks the delivery status of his order")
    public void user_checks_the_delivery_status_of_his_order(String userName) {
        orderStatus = orderRegistry.getOrderStatus(order.getOrderID(), customer);
    }

    @When("{string} wants to check the delivery status of {string} order")
    public void user_checks_the_delivery_status_of_another_user(String userName,String ownerName) {
        orderStatus = orderRegistry.getOrderStatus(order.getOrderID(), new CampusUser(userName));
    }

    @Then("he should get the delivery status of the order")
    public void he_should_get_the_delivery_status_of_the_order() {
        assert orderStatus == order.getOrderStatus();
    }


    @Given("a {string} who doesn't have an order")
    public void a_who_doesn_t_have_an_order_with_an_order_id(String customerName) {
        customer = new CampusUser(customerName);
        CampusUser customer2 = new CampusUser();
        order = new Order(customer2);
        orderRegistry.save(order);
    }

    @Then("he can't get the delivery status of the order")
    public void heCanTGetTheDeliveryStatusOfTheOrder() {
        assert orderStatus == null;
    }
}
