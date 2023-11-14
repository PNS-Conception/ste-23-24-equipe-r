Feature: Get Statistical Insights
  Scenario: Viewing Order Volumes Over Time
    Given an Order has been created but not "DELIVERED"
    When a "User" retrieves statistical data on order volumes
    Then the Order is included in the order volume data

  Scenario: Restaurant Managers Access Restaurant-Specific Data
    Given an Order from a "Restaurant" has been created but not "DELIVERED"
    When a "Restaurant Manager" retrieves statistical data on order volumes per restaurant
    Then the Order is included in the order volume data per restaurant

  Scenario: Accessing Delivery Location Data
    Given an Order from "Restaurant" is created by a "User" with the delivery location "RESIDENCE_HALL"
    And the status is not "DELIVERED"
    When User retrieves statistical data on order volumes per delivery location
    Then the "RESIDENCE_HALL" location is included in the delivery location data

