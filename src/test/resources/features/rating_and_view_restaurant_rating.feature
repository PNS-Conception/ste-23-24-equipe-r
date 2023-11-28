# Created by Saad at 31/10/2023
Feature: Rating and View Restaurant Rating
  Scenario Outline: CampusUser1 rates Restaurant
    Given Restaurant A has a "<list>" of rating
    When the CampusUser1 rates the restaurant 5 out of 5
    Then the rating of this restaurant should be 4.0 out of 5
    Examples:
      | list          |
      | 3.5, 3.6, 3.7 |
  Scenario Outline: CampusUser2 Checks Restaurant Rating
    Given Restaurant A has a "<list>" of rating
    When the CampusUser2 checks the rating of the restaurant
    Then the rating of this restaurant should be 3.6 out of 5
    Examples:
      | list          |
      | 3.5, 3.6, 3.7 |
