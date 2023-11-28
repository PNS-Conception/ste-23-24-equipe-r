Feature: access previous orders
  Scenario: Successfully viewing previous orders
    Given a logged-in Campus user 'Karim' and a list of previous orders
    When the campus user Karim clicks on show previous orders
    Then he should get a list of all his previous orders

  Scenario: No previous orders to view
    Given a logged-in Campus user 'Karim'
    When the campus user Karim clicks on show previous orders
    And there is no previous orders
    Then the list of previous orders should be empty