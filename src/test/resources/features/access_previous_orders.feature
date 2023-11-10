Feature: access previous orders
  Scenario: Successfully viewing previous orders
    Given a logged-in Campus user 'Karim' and a list of previous orders
    When the campus user Karim clicks on show previous orders
    Then a list of all his previous orders in reverse chronological order

  Scenario: No previous orders to view
    Given a logged-in Campus user 'Karim'
    When the campus user Karim clicks on show previous orders
    And there is no previous orders
    Then Karim should see a message saying "No previous orders found."