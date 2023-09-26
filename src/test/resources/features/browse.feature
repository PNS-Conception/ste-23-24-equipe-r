Feature: Menu Retrieval

  Scenario: User retrieves the list of menus
    Given "John" is on the menu page
    When "John" requests the list of menus
    Then display a list of menus for "John"