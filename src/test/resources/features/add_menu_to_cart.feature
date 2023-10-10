# Created by Jamal Eddine at 10/10/2023
Feature: Adding an menu to the cart

  Background:
    Given "Marcel" is a campus user

  Scenario: Add an existing menu to the cart
    When he selects a menu "Pizza" from a restaurant "restau"
    Then the menu is added to the cart

