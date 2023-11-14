Feature: deliver order
    Scenario: assign a delivery person to an order
    Given an order is ready to be delivered
    When a delivery person "John" want to deliver the order
    Then "John" should be assigned to deliver the order
    And the delivery status should be "IN_PROGRESS"
