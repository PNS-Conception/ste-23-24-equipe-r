# Created by Jamal Eddine at 11/1/2023
Feature: Remove restaurant
  # Enter feature description here

Scenario: Remove restaurant
  Given The list of restaurants contains those restaurants
          | name |
            | La bonne fourchette |
            | Le petit poucet |
  When The campus admin removes the restaurant named "La bonne fourchette"
  Then "La bonne fourchette" should be removed from the list of restaurants