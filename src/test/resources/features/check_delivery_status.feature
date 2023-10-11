# Created by Jamal Eddine at 10/11/2023
Feature: Checking the delivery status of an order

  Scenario:
    Given a "Campus user" have an order with an order Id "216"
    When "Campus user" checks the delivery status of the order
    Then he should get the delivery status of the order

  Scenario:
    Given a "Campus user 2" who doesn't have an order with an order Id "216"
    Then he can't get the delivery status of the order