Feature: Place an order

  Background:
    Given "Brahim" is a campus user with a balance of 100.00
    And a restaurant "Pizza Hut" exists with the following details
      | Opening Time | Closing Time | Capacity |
      | 09:00        | 20:00        | 10       |
    And the restaurant "Pizza Hut" has the following menus
      | Menu Name          | Price |
      | Margherita Pizza   | 10.00 |
      | Veggie Pizza       | 12.00 |
      | Meat Lovers Pizza  | 14.00 |
      | BBQ Chicken Pizza  | 13.00 |

  Scenario: The cart is empty by default
    When "Brahim" checks his cart's contents
    Then there is 0 menus in his cart

  Scenario: Adding menu to a cart
    When "Brahim" chooses 1 x "Margherita Pizza"
    And "Brahim" checks his cart's contents
    Then there is 1 menus in his cart
    And the cart contains the menus : 1 x "Margherita Pizza"

  Scenario: Adding multiple menus to a cart
    When "Brahim" chooses 2 x "Veggie Pizza"
    And "Brahim" chooses 1 x "Meat Lovers Pizza"
    And "Brahim" checks his cart's contents
    Then there is 3 menus in his cart
    And the cart contains the menus : 2 x "Veggie Pizza"
    And the cart contains the menus : 1 x "Meat Lovers Pizza"

  Scenario: Modifying the menus in a cart
    When "Brahim" chooses 2 x "BBQ Chicken Pizza"
    And "Brahim" chooses 3 x "Meat lovers Pizza"
    And "Brahim" chooses 3 x "BBQ Chicken Pizza"
    And "Brahim" checks his cart's contents
    Then the cart contains the menus : 5 x "BBQ Chicken Pizza"
    And the cart contains the menus : 3 x "Meat lovers Pizza"

  Scenario: Deleting menus from the cart
    When "Brahim" chooses 5 x "Veggie Pizza"
    And "Brahim" removes 2 x "Veggie Pizza"
    And "Brahim" checks his cart's contents
    Then there is 3 menus in his cart
    And the cart contains the menus : 3 x "Veggie Pizza"

  Scenario: Confirming price of cart
    When "Brahim" chooses 3 x "BBQ Chicken Pizza"
    And "Brahim" chooses 2 x "Margherita Pizza"
    And "Brahim" chooses 1 x "Veggie Pizza"
    And "Brahim" checks his cart's contents
    Then there is 6 menus in his cart
    And the price of "Brahim"'s cart is 71.00

  Scenario: placing an order in an available timeslot
    Given timeslot "15:00" has capacity 5
    When "Brahim" chooses 2 x "Margherita Pizza"
    And "Brahim" chooses 1 x "Veggie Pizza"
    And chooses timeslot "15:00" and delivery location "LIBRARY"
    And "Brahim" confirms and pays for the cart
    Then timeslot "15:00" should have capacity 2
    And the price of the order is 32.00
    And the order status is "PREPARING"
    And "Brahim" checks his cart's contents
    And there is 0 menus in his cart

  Scenario: placing an order with insuffisant balance in an available timeslot
    Given timeslot "15:00" has capacity 5
    And "Brahim" has a balance of 1.00
    When "Brahim" chooses 2 x "Margherita Pizza"
    And "Brahim" chooses 1 x "Veggie Pizza"
    And chooses timeslot "15:00" and delivery location "LIBRARY"
    And "Brahim" tries to confirm and pay for the cart
    Then a "InsufficientBalanceException" should be thrown

  Scenario: getting discount for order with more than 10 menus
    Given timeslot "15:00" has capacity 15
    And "Brahim" has a balance of 1000.00
    When "Brahim" chooses 11 x "Margherita Pizza"
    And "Brahim" chooses 1 x "Veggie Pizza"
    And chooses timeslot "15:00" and delivery location "LIBRARY"
    And "Brahim" confirms and pays for the cart
    Then the price of the order is 109.8
