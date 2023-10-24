Feature: Delivery Confirmation
  As a delivery person, I want to confirm the delivery so that the order status is updated to "DELIVERED".

  Scenario: Delivery person confirms the delivery
    Given there is an order with status "READY_FOR_DELIVERY"
    When the delivery person delivers the order
    Then the order status should be "DELIVERED"
