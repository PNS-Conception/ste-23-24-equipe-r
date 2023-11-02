package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.groupOrder.GroupOrder;
import fr.unice.polytech.steats.groupOrder.GroupOrderRepository;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

;import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



public class AddSubOrderToGroupOrderSteps {

    CampusUser CU ;
    Optional<GroupOrder> GO ;

    GroupOrderRepository GORepo ;

    Order Order ;
    List<Order> suborders ;
    @Given("I am a logged-in Campus User and a group Order")
    public void i_am_a_logged_in_campus_user() {
        // Write code here that turns the phrase above into concrete actions
        CU = new CampusUser();
    }

    @When("I navigate to the Group Orders section")
    public void i_navigate_to_the_section() {
        GORepo = new GroupOrderRepository();
    }

    @When("I select an existing group order with the ID {string}")
    public void i_select_an_existing_group_order(String string) {
        UUID uuid = UUID.fromString(string);
        GO = GORepo.findByID(uuid);

    }

    @When("I add my selections to the sub-order")
    public void i_add_my_selections_to_the_sub_order() {
        Order = new Order();
        Order.setTotalMenus(1);

    }

    @When("click the Add Sub-Order button")
    public void click_the_button() {

        if(GO.isPresent()) {
            suborders = GO.get().getSubOrders();
            suborders.add(Order);
        }

    }

    @Then("my sub-order should be added to the selected group order")
    public void my_sub_order_should_be_added_to_the_selected_group_order() {
        GO.ifPresent(groupOrder -> groupOrder.setSubOrders(suborders));
    }


    @When("I select a group order with the ID {string} that is closed for new sub-orders")
    public void i_select_a_group_order_with_the_id_that_is_closed_for_new_sub_orders(String string) {
        UUID uuid = UUID.fromString(string);
        GO = GORepo.findByID(uuid);




    }

    @Then("I should see an error message stating {string}")
    public void i_should_see_an_error_message_stating(String string) {
        // Write code here that turns the phrase above into concrete actions
        GO.ifPresent(groupOrder -> assertFalse(string,groupOrder.isOpen()));
    }
    @When("I don't add any selections to the sub-order")
    public void i_don_t_add_any_selections_to_the_sub_order() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(Order.getTotalMenus()==0);
    }
}
