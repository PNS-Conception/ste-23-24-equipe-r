Feature: Order confirmation

  Background:
    Given "Marcel" is an authenticated CampusUser
    And he has the following items in my cart
      | menuName | price |
      | Pizza    | 10.0  |
      | Burger   | 8.0   |

  Scenario: Successfully confirming an order
    When I choose the available delivery location "CampusCenter"
    And I choose the available delivery time "12:00-1:00"
    And I confirm the order
    Then an order gets created using the cart
