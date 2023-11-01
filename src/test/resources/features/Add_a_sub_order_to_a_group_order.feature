Feature: Add a Sub-Order to a Group-Order
  Scenario: Successfully adding a sub-order to an existing group order
    Given I am a logged-in Campus User and a group Order
    When I navigate to the Group Orders section
    And I select an existing group order with the ID '550e8400-e29b-41d4-a716-446655440000'
    And I add my selections to the sub-order
    And click the Add Sub-Order button
    Then my sub-order should be added to the selected group order

  Scenario: Unsuccessful attempt due to group order being closed
    Given I am a logged-in Campus User and a group Order
    When I navigate to the Group Orders section
    And I select a group order with the ID '550e8400-e29b-41d4-a716-446655440000' that is closed for new sub-orders
    Then I should see an error message stating "This group order is closed for new sub-orders"

  Scenario: Unsuccessful attempt due to no selection
    Given I am a logged-in Campus User and a group Order
    When I navigate to the Group Orders section
    And I select an existing group order with the ID '550e8400-e29b-41d4-a716-446655440000'
    And I don't add any selections to the sub-order
    And click the Add Sub-Order button
    Then I should see an error message stating "You must make a selection to add a sub-order"