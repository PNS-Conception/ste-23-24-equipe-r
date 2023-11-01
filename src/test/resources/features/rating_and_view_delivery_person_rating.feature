# Created by Saad at 31/10/2023
Feature: Rating and View Delivery Person Rating

  Scenario: CampusUser1 rates a Delivery Person
    Given Delivery Person is rated at 2 out of 5
    When the CampusUser1 rates the delivery person 4 out of 5
    Then the rating of this delivery person should be 3 out of 5

  Scenario: CampusUser2 Checks Delivery Person Rating
    Given Delivery Person is rated at 3 out of 5
    When the CampusUser2 checks the rating of the delivery person
    Then the rating of this delivery person should be 3 out of 5
