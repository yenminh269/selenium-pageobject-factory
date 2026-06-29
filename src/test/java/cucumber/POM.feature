@tag
  Feature: Purchase the order from Ecommerce Website
    Background:
      Given I landed on Ecommerce Page
    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER" Verify the message

      Examples:
        | name                   | password  | productName |  |
        | taesicbadao6@gmail.com | 11626Oak! | ZARA COAT 3 |  |


