Feature: Adding a menu to the cart

  Background:
    Given "Marcel" is a campus user
    And a restaurant "Pizza Hut" exists with the following menus
      | Menu Name       | Price |
      | Margherita Pizza| 10.00 |
      | Veggie Pizza    | 12.00 |
      | Meat Lovers Pizza | 14.00 |
      | BBQ Chicken Pizza | 13.00 |

  Scenario: Add an existing menu to the cart
    When "Marcel" selects a menu "Margherita Pizza" from "restau"
    Then the menu is added to the cart
