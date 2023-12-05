Feature: Customize Menu Selection
  Scenario Outline: Customize Menu Selection
    Given a logged in campus user 'John'
    And a chosen Menu 'Fish And Chips' with the price 10
    And a list of possible modifications for each menu item 'sauce', 'dessert', 'add-ons'
    When the user chooses the sauce '<sauce>', the dessert '<dessert>', and the add-ons '<add-ons>'
    Then the selected options are added and their price is calculated
    Examples:
      | sauce       | dessert        | add-ons            |
      | white sauce | cheese cake    | grilled vegetables |
      | red sauce   | pineapple cake | mushed potatoes    |


  Scenario: ErrorHandling
    Given a logged in campus user 'John'
    And a chosen Menu 'Fish And Chips' with the price 10
    And a list of possible modifications for each menu item 'sauce', 'dessert', 'add-ons'
    When the user provides invalid menu customization choices sauce 'invalid_sauce', dessert 'invalid_dessert',add-ons 'invalid_addons'
    Then the menu remains unchanged


  Scenario: Commenting a menu
    Given a logged in campus user 'John'
    And a chosen Menu 'Fish And Chips' with the price 10
    When the user writes a comment 'really good price/value ratio'
    Then the menu is commented





