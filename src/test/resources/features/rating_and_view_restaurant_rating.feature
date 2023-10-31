Feature: Rating and View Restaurant Rating
  Scenario: CampusUser rates
    Given a Restaurant is rated at 2 stars
    When the CampusUser rates the restaurant 4 stars
    Then  the rating of this restaurant should be 3 star