Feature: Order Acceptance for Delivery Preparation
  Scenario: No Order Found
    Given "restaurant staff" member is logged into the "Restaurant" system
    And there is no order awaiting acceptance
    When the "restaurant staff" attempts to accept an order
    Then the "Restaurant" system should display an error message: "No order found for acceptance"