Feature: Menu Retrieval

  Scenario: "John" retrieves the list of menus
    Given "John" is on the menu page
    When "John" requests the list of menus
    Then the system should display a list of menus for "John"