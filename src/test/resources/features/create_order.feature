Feature: Order confirmation

  Background:
    Given "Marcel" is an authenticated CampusUser
    And he has the following items in his cart from restaurant "restau"
      | menuName | price |
      | Pizza    | 10.0  |
      | Burger   | 8.0   |

  Scenario: Successfully confirming an order
    When he chooses the available delivery location "Library"
    And he chooses the available slot "12:00"
    Then an order gets created using the cart
