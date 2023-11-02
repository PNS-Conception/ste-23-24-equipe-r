# Created by Saad at 31/10/2023
Feature: Rating and View Delivery Person Rating

  Scenario Outline: CampusUser1 rates a Delivery Person
    Given Delivery Person DP1 has a "<list>" of ratings
    When the CampusUser1 rates the delivery person with 5 out of 5
    Then the rating of this delivery person should be 4.0 out of 5

    Examples:
      | list          |
      | 3.5, 3.6, 3.7 |

  Scenario Outline: CampusUser2 Checks Delivery Person Rating
    Given  Delivery Person DP1 has a "<list>" of ratings
    When the CampusUser2 checks the rating of the delivery person
    Then the rating of this delivery person should be 3.6 out of 5

    Examples:
      | list          |
      | 3.5, 3.6, 3.7 |