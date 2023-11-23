package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.List;

import static fr.unice.polytech.steats.order.OrderStatus.PREPARING;
import static fr.unice.polytech.steats.users.CampusUserStatus.STAFF;
import static org.junit.Assert.assertEquals;

public class GetOrdersWaitingForPreparationSteps {
    Restaurant restaurant;
    CampusUser staff;
    OrderRepository orderRepository;
    OrderManager orderManager;
    List<Order> ordersWaitingForPreparation;

    public GetOrdersWaitingForPreparationSteps(FacadeContainer container) {
        orderManager = container.orderManager;
        orderRepository = container.orderRepository;
    }

    @Given("a restaurant staff {string} working at {string}")
    public void a_restaurant_staff_working_at(String staffName, String restaurantName) {
        restaurant = new Restaurant(restaurantName);
        staff = new CampusUser(staffName,STAFF);
    }
    @Given("the restaurant has {int} orders waiting for preparation")
    public void a_restaurant_with_orders_waiting_for_preparation(int ordersNumber) throws Exception {
        for(int i = 0; i < ordersNumber; i++){
            LocalDate orderDate = LocalDate.now();
            Order order = new Order(restaurant, new Menu("MaxBurger",12), orderDate);
            orderRepository.save(order, order.getId());
        }
        for(int i = 0; i < 6; i++){
            LocalDate orderDate = LocalDate.now();
            Order order = new Order(restaurant, new Menu("MaxBurger",12), orderDate);
            order.setStatus(PREPARING);
            orderRepository.save(order, order.getId());
        }
    }
    @When("the restaurant staff Karim clicks on get orders waiting for preparation")
    public void the_restaurant_staff_karim_clicks_on_get_orders_waiting_for_preparation() {
        ordersWaitingForPreparation = orderManager.getOrdersWaitingForPreparation(restaurant);
    }
    @Then("he should get a list of {int} orders waiting for preparation")
    public void he_should_get_a_list_of_orders_waiting_for_preparation(int ordersNumber) {
        assertEquals(ordersNumber, ordersWaitingForPreparation.size());
    }

}
