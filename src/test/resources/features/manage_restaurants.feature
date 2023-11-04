Feature: Manage restaurants

  Background:
    Given the campus has 3 restaurants
            | Burger King  |
            | KFC          |
            | Subway       |

  Scenario: Add new restaurant
    Given a restaurant "McDonald's" match the requirements
    When the campus admin attempts to add a new restaurant "McDonald's"
    Then the campus should have 4 restaurants
    And the restaurant "McDonald's" should be in the campus

  Scenario: Add new restaurant with invalid name
    When the campus admin attempts to add a new restaurant "Subway"
    Then the campus should have 3 restaurants

  Scenario: Remove restaurant
    When The campus admin removes the restaurant "KFC"
    Then the campus should have 2 restaurants
    And the restaurant "KFC" should be removed from the campus


