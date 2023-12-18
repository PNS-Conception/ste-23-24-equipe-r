Feature: Place a Buffet Order

  Background:
    Given "Alex" is a campus user
    And a restaurant "Au Trianon" exists with the following details
      | Opening Time | Closing Time | Capacity |
      | 09:00        | 20:00        | 100      |
    And the restaurant "Au Trianon" offers a buffet called "Breakfast Buffet" priced at 15.00 per participant

  Scenario: Choosing a buffet menu for an order
    When "Alex" chooses the "Breakfast Buffet" at "Au Trianon" for 20 participants
    Then the price of "Alex"'s cart is 300.0

  Scenario: Placing a buffet order with a specific delivery time
    Given "Alex" chooses the "Breakfast Buffet" at "Au Trianon" for 20 participants
    And timeslot "2024-01-15 10:00" at "Au Trianon" can accommodate a buffet for 20 participants
    And chooses delivery time "2024-01-15 12:00" of the restaurant "Au Trianon" and delivery location "Library" for the buffet
    And "Alex" confirms and pays for the buffet order for 20 participants
    Then the order status is "PREPARING" for the buffet order
    And timeslot "2024-01-15 10:00" should have capacity 0
    And the price of the buffet order is 300.0
