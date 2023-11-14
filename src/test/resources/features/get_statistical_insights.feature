Feature: Get Statistical Insights
  Scenario: Viewing Order Volumes Over Time
    Given an Order has been created but not "DELIVERED"
    When a "User" retrieves statistical data on order volumes
    Then the Order is included in the order volume data

    Scenario: Restaurant Managers Access Restaurant-Specific Data
      Given an Order from a "Restaurant" has been created but not "DELIVERED"
      When a "Restaurant Manager" retrieves statistical data on order volumes per restaurant
      Then the Order is included in the order volume data per restaurant

