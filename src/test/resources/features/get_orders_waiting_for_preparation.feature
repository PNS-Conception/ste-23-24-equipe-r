Feature: access orders waiting for preparation
  Scenario: Successfully viewing orders waiting for preparation
    Given a restaurant staff "Karim" working at "Food place"
    And the restaurant has 3 orders waiting for preparation
    When the restaurant staff Karim clicks on get orders waiting for preparation
    Then he should get a list of 3 orders waiting for preparation