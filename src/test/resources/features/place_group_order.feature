Feature: Place a group order
  scenarios concerning the group order functionality

  Background:
    Given "Chris" is a campus user
    And "Jotaro" is a campus user
    And a restaurant "McDonalds" exists with the following details
      | Opening Time | Closing Time | Capacity |
      | 10:25        | 18:10        | 10       |
    And the restaurant "McDonalds" has the following menus
      | Menu Name          | Price |
      | McChicken   | 10.00 |
      | Big Tasty       | 12.00 |
      | Chicken McNuggets  | 14.00 |
      | Filet-o-Fish  | 13.00 |

  Scenario: Create a group order
    When "CR7" requests to create a group order
    And chooses timeslot "15:00" of the restaurant "McDonalds" and delivery location "Library"
    Then a group order is created with a unique code
    And the group order is open

  Scenario: Add a sub order to an existing group order
    Given a group order exists with the code "5XSD1S"
    And group order "5XSD1S" is set with timeslot 12:25 and location "Student Center"
    When "Jotaro" joins the group order using the code "5XSD1S"
    And "Jotaro" orders and pays for 2 x "McChicken"
    Then the price of the order is 20.00
    And "Jotaro"'s order should be set with timeslot 12:25 and location "Student Center"
    And group order "5XSD1S" should have one extra order

  Scenario: Add multiple sub orders to an existing group order
  Scenario: Close the group order



