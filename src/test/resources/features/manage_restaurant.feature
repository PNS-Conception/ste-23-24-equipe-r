# Created by Jamal Eddine at 03-11-23
Feature: Manage Restaurant

  Scenario: Update Menu

    Given the owner of the restaurant "Delicious Eats," with a current menu including
      |Pizza|32.00|
      |Pasta|22.00|
      |Salads|41.00|


    When he adds to the list of menus
      |Burgers|56.00|
      |Soup|12.00|

    Then the menu should contain
      |Pizza|32.00|
      |Pasta|22.00|
      |Salads|41.00|
      |Burgers|56.00|
      |Soup|12.00|

  Scenario: Update Operating Hours

    Given the owner of the restaurant "Delicious Eats" with current operating hours from "10:00" to "22:00" with a capacity of 10 dishes per ten minutes

    When he updates the operating hours to be from "11:00" to "23:00" with a capacity of 10 dishes per ten minutes for each slot

    Then the operating hours should be from "11:00" to "23:00" with a capacity of 10 dishes per ten minutes for each slot

  Scenario: Update Production Capacity

    Given the owner of the restaurant "Delicious Eats" with a current production capacity of 10 dishes per ten minutes

    When he updates the production capacity to 15 dishes per ten minutes

    Then the production capacity should be 15 dishes per ten minutes