Feature: Adding a new restaurant

  Scenario: Campus Admin Successfully Adds a New Restaurant
    Given the campus admin "Adam" is on the add restaurant page
    And there is a new restaurant "Etoile rouge" to be added.
    When the campus admin "Adam" to add a restaurant named "Etoile rouge"
    And The restaurant fills out all the necessary checklists.
    Then the restaurant "Etoile rouge" can be added successfully
    And the campus admin "Adam" should receive a confirmation message "Restaurant created succesfully ..." indicating the successful addition of the restaurant "Etoile rouge".

  Scenario: Campus Admin Attempts to Add a New Restaurant but Faces Errors
    Given the campus admin "Adam" is on the add restaurant page
    And there is a new restaurant "Etoile rouge" to be added.
    When the campus admin attempts to add a restaurant named "Etoile rouge"
    And The restaurant "Etoile rouge" doesn't fill all the application requirements.
    Then the application will raise an error "Sorry this restaurant cannot be added due to system policy".