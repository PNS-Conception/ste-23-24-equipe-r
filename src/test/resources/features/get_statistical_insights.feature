Feature: Get Statistical Insights
  Scenario: Viewing Order Volumes Over Time
    Given an Order has been created but not "DELIVERED"
    When a "User" retrieves statistical data on order volumes
    Then the Order is included in the order volume data
