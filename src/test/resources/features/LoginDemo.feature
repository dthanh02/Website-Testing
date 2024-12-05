Feature: Test login functionality

  Scenario Outline: Check login is successful with valid credentials
    Given browser is open
    And user is on login page
    When user enters <username> and <password>
    And user clicks on login
    And user is navigated to the home page
    Then user clicks log out

    Examples: 
      | username        | password |
      | admin@gmail.com |     1111 |
      | user1@gmail.com |     1112 |
      |                 |          |
      | 123@            |     1111 |

  
  
  
  
  
  
  
  
  
  
  
  
      
      #| thao@gmail.com  |     1111 |
      #| thanh@gmail.com |     1111 |
      #| user@gmail.com  |     1111 |