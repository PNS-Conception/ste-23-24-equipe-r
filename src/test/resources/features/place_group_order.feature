Feature: Place a group order
  scenarios concerning the group order functionality

  Background:
    Given "Chris" is a campus user
    And "Jotaro" is a campus user
    And "Aizen" is a campus user
    Given "Gojo" is a campus user
    And a restaurant "McDonalds" exists with the following details
      | Opening Time | Closing Time | Capacity |
      | 10:25        | 18:10        | 10       |
    And the restaurant "McDonalds" has the following menus
      | Menu Name          | Price |
      | McChicken   | 10.00 |
      | Big Tasty       | 12.00 |
      | Chicken McNuggets  | 14.00 |
      | Filet-o-Fish  | 13.00 |
    And a group order exists of user "Chris" with restaurant "McDonalds" and delivery time "14:30" and location "Student Center"

  Scenario: Create a group order
    When "Jotaro" requests to create a group order
    And chooses timeslot "15:05" of the restaurant "McDonalds" and delivery location "Library" for group order
    Then a group order is created with a unique code
    And the group order is in "open" status

  Scenario: Add a sub order to an existing group order
    When "Jotaro" joins the group order using the code
    And "Jotaro" orders and pays for 2 x "McChicken"
    Then the price of "Jotaro"'s order is 20.00
    And "Jotaro"'s order should be set with timeslot "12:25" and location "Student Center"
    And the group order should have 1 order
    And "Jotaro"'s order should be set with delivery time "14:30" and location "Student Center"
    And group order "5XSD15SS" should have 1 order

  Scenario: Add multiple sub orders to an existing group order
    When "Jotaro" joins the group order "5XSD15SS"
    And "Jotaro" orders and pays for 1 x "Big Tasty"
    And "Aizen" joins the group order "5XSD15SS"
    And "Aizen" orders and pays for 2 x "Chicken McNuggets"
    Then the price of "Jotaro"'s order is 12.00
    And the price of "Aizen"'s order is 28.00
    And group order "5XSD15SS" should have 2 order

  Scenario: Close the group order
    When "Chris" closes the group order
    Then the group order is in "closed" status