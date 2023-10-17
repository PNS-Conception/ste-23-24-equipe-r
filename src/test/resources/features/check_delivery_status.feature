# Created by Jamal Eddine at 10/11/2023
Feature: Checking the delivery status of an order

  Background:
    Given a "Campus user" have an order

  Scenario:
    When "Campus user" checks the delivery status of his order
    Then he should get the delivery status of the order

  Scenario:
    When "Campus user 2" wants to check the delivery status of "Campus user" order
    Then he can't get the delivery status of the order