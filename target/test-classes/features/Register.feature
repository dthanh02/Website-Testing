Feature: Test register functionality

  Scenario Outline: Check register is successful with valid credentials
    Given google is open
    And user is on the registration page
    And user login admin account
    When user enter <name> , <username> , <password>
    And clicks on the register button
    Then user succesfully register

    Examples: 
      | name  | username         | password |
      | thanh | thanh3@gmail.com |     1111 |
      |       |                  |          |
      | thao  | thanh            |     1111 |
      | thanh | thanh2@          |     1111 |


