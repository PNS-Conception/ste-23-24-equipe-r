# Created by Saad at 17/10/2023
Feature: Deliver an order
  Scenario: No Order Ready for delivery
    Given Order has the status "PREPARING"
    When the "delivery person" consult the list of ready orders for delivery
    Then the list should contains "0" order

    Scenario: Order Ready for delivery
      Given Order has a status "READY_FOR_DELIVERY"
      When the "delivery person" consult the list of ready orders for delivery
      Then the list should contains at least "1" order