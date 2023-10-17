# Created by Saad at 10/10/2023
Feature: Order Acceptance for Delivery Preparation

  Scenario: No Order Found
    Given "restaurant staff" member is logged into the "Restaurant" system
    And there is no order awaiting acceptance
    When the "restaurant staff" consult the list of orders
    Then the "Restaurant" system should display an error message: "No order found for acceptance"

  Scenario: Accepting an Order
    Given "restaurant staff" member is logged into the "Restaurant" system
    And there is an order awaiting acceptance
    When the "restaurant staff" member accepts the order for preparation
    Then the order status becomes ACCEPTED
