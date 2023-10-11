# Created by Saad at 04/10/2023
Feature: Login

  Scenario Outline: Logging in with valid credentials
    Given <string> is on the login page
    When <string> enters the username : <string2>
    And <string> enters the password : <string21>
    Then the <string1> should be visible
    Examples:
      | string | string2    | string21         | string1               |
      | "John" | "21"       | "password"       | "create order option" |