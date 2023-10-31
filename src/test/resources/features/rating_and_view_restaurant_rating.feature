# Created by Saad at 31/10/2023
Feature: Rating and View Restaurant Rating
  Scenario: CampusUser1 rates Restaurant
    Given Restaurant A is rated at 2 stars
    When the CampusUser1 rates the restaurant 4 stars
    Then the rating of this restaurant should be 3 stars

  Scenario: CampusUser2 Checks Restaurant Rating
    Given Restaurant A is rated at 3 stars
    When the CampusUser2 checks the rating of the restaurant
    Then the rating of this restaurant should be 3 stars
