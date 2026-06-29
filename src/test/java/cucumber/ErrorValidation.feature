@tag
Feature: Error validation

  @ErrorValidation
  Scenario Outline: Negative Test of Logged in
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name             | password  |
      | taesic@gmail.com | 11626Oak! |


