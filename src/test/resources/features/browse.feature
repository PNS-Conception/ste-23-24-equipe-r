# Created by Saad at 26/09/2023
Feature: Menu Retrieval

  Scenario: User retrieves the list of menus
    Given "John" is on the menu page
    When "2023-09-26 19:00:00" as a time slot is chosen
    Then "Dinner Menu" is displayed