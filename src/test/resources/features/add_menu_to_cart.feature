# Created by Jamal Eddine at 10/10/2023
Feature: Adding an menu to the cart

  Background:
    Given "Marcel" is a campus user and a restaurant "restau" who has a menu "Pizza"

  Scenario: Add an existing menu to the cart
    When "marcel" selects a menu "Pizza" from "restau"
    Then the menu is added to the cart