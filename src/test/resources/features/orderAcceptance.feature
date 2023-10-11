# Created by Saad at 10/10/2023
Feature: Order Acceptance for Delivery Preparation

  Scenario: No Order Found
    Given "restaurant staff" member is logged into the "Restaurant" system
    And there is no order awaiting acceptance
    When the "restaurant staff" attempts to accept an order
    Then the "Restaurant" system should display an error message: "No order found for acceptance"

  Scenario: Accepting an Order
    Given "restaurant staff" member is logged into the "Restaurant" system
    And there is an "order" with ID "1234" awaiting acceptance
    When the "restaurant staff" member accepts the order with ID "1234" for preparation
    Then the "Restaurant" system should mark the order with ID "1234" as "ACCEPTED"
