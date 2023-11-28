Feature: Receiving Notifications

  Scenario: Notifying a Campus user when the order is ready to be delivered

    Given a logged-in Campus user as the order owner
    And delivery people ready to pick an order
    And an order with the status PREPARING
    When the order registry sets the order status to READY_FOR_DELIVERY
    Then a delivery person is notified
