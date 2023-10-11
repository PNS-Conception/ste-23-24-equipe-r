Feature: Check Food Delivery Status
  Scenario: A campus user checks the delivery status of their order
    Given an existing food "order"
    When the "campus user" selects the "order" and requests the delivery "status"
    Then the application displays "the current status" of the order