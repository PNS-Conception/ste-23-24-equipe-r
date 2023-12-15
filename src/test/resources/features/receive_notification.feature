Feature: Receiving Notifications

  Scenario: Notifying a Campus user when the order is ready to be delivered

    Given a logged-in Campus user as the order owner
    And delivery people ready to pick an order
    And an order with the status PREPARING
    When the order registry sets the order status to READY_FOR_DELIVERY
    Then a delivery person is notified

  Scenario: Notifying a Campus user when the order is created
    Given "John" is a campus user
    When "John" creates an order
    Then "John" is notified

  Scenario: Notifying a restaurant about the planned pickup time
    And a restaurant "Burger King" exists with the following details
      | Opening Time | Closing Time | Capacity |
      | 09:00        | 20:00        | 10       |
    And the restaurant "Burger King" has the following menus
      | Menu Name          | Price |
      | Veggie Pizza   | 10.00 |
    And "John" is a campus user
    When "John" chooses the restaurant "Burger King"
    And "John" chooses 1 x "Veggie Pizza"
    And chooses delivery time "2024-01-15 17:30" of the restaurant "Burger King" and delivery location "Library"
    And "John" confirms and pays for the cart
    Then "Burger King" is notified about the planned pickup time

