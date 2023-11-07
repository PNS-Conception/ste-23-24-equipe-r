Feature: Dynamic Pricing Based on User Type
  Scenario Outline: Eligible Restaurant for Discounts
    Given a CampusUser with a profile indicating "<type>" as their user type.
    And a restaurant that is "ELIGIBLE_FOR_DISCOUNT"
    When processing a payment for a "menu" with the following "<price>" base on the user type
    Then the system calculate the final price as "<finalPrice>"
    Examples:
      | type      | price | finalPrice |
      | STUDENT   | 10.00 | 10.00      |
      | FACULTY   | 15.00 | 15.00      |
      | STAFF     | 20.00 | 20.00      |
