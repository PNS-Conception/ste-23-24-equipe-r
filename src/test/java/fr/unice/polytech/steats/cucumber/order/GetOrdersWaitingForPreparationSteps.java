package fr.unice.polytech.steats.cucumber.order;

import fr.unice.polytech.steats.cucumber.picosetup.FacadeContainer;
import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderLocator;
import fr.unice.polytech.steats.steatspico.repositories.OrderRepository;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static fr.unice.polytech.steats.steatspico.entities.order.OrderStatus.PREPARING;
import static fr.unice.polytech.steats.steatspico.entities.order.OrderStatus.WAITING_FOR_PREPARATION;
import static fr.unice.polytech.steats.steatspico.entities.users.CampusUserStatus.STAFF;
import static org.junit.Assert.assertEquals;

public class GetOrdersWaitingForPreparationSteps {
    Restaurant restaurant;
    CampusUser staff;
    final OrderRepository orderRepository;
    final OrderLocator orderLocator;
    List<Order> ordersWaitingForPreparation;

    public GetOrdersWaitingForPreparationSteps(FacadeContainer container) {
        orderLocator = container.orderLocator;
        orderRepository = container.orderRepository;
    }

    @Given("a restaurant staff {string} working at {string}")
    public void a_restaurant_staff_working_at(String staffName, String restaurantName) {
        restaurant = new Restaurant(restaurantName);
        staff = new CampusUser(staffName,STAFF);
    }
    @Given("the restaurant has {int} orders waiting for preparation")
    public void a_restaurant_with_orders_waiting_for_preparation(int ordersNumber) {
        CampusUser user = new CampusUser("lambda");
        if (!user.getCart().getRestaurantMenusMap().containsKey(restaurant)) {
            user.getCart().getRestaurantMenusMap().put(restaurant, new HashMap<>());
        }
        OrderDetailsBuilder builder = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(user)
                .deliveryTime(LocalDateTime.now())
                .deliveryLocation(DeliveryLocation.LIBRARY);
        OrderDetails orderDetails1 = builder.build();
        OrderDetails orderDetails2 = builder.orderOwner(new CampusUser("other")).build();
        for(int i = 0; i < ordersNumber; i++){

            Order simpleOrder = new SimpleOrder(orderDetails1);
            simpleOrder.setStatus(WAITING_FOR_PREPARATION);
            orderRepository.save(simpleOrder, simpleOrder.getId());
        }
        for(int i = 0; i < 6; i++){
            Order simpleOrder = new SimpleOrder(orderDetails2);
            simpleOrder.setStatus(PREPARING);
            orderRepository.save(simpleOrder, simpleOrder.getId());
        }
    }
    @When("the restaurant staff Karim clicks on get orders waiting for preparation")
    public void the_restaurant_staff_karim_clicks_on_get_orders_waiting_for_preparation() {
        ordersWaitingForPreparation = orderLocator.getOrdersByStatus(restaurant, WAITING_FOR_PREPARATION);
    }
    @Then("he should get a list of {int} orders waiting for preparation")
    public void he_should_get_a_list_of_orders_waiting_for_preparation(int ordersNumber) {
        assertEquals(ordersNumber, ordersWaitingForPreparation.size());
    }

}
