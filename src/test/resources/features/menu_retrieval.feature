# Created by ibrah at 09/10/2023
Feature: Browse menus from different campus restaurants

  Scenario: Internet user browses menus with multiple restaurants available
    Given a list of campus restaurants with multiple menus
    When an internet user visits the restaurant page
    Then the menus from different campus restaurants should be displayed

  Scenario: Internet user browses menus with no restaurants available
    Given an empty list of campus restaurants
    When an internet user visits the restaurant page
    Then a message "No restaurants available" should be displayed

  Scenario: Internet user browses menus with one restaurant available
    Given a list of campus restaurants with one menu
    When an internet user visits the restaurant page
    Then only the menu from that single restaurant should be displayed

  Scenario: Internet user browses menus with some restaurants having no menus
    Given a list of campus restaurants where some restaurants have no menus
    When an internet user visits the restaurant page
    Then the menus from restaurants that have them should be displayed
    And a message should indicate the restaurants with no menus available
