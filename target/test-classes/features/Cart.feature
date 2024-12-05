Feature: Purchase Items

Scenario Outline: Purchase items from online store
    Given browser is opening
    And the user is on the login page
    When user enter <username> and <password>
    And the user adds random products to the cart
    And the user proceeds to checkout
    And user enters info
    And user choose with <skip_list_item_click> and <skip_radio_button_click> 
    Then the user completes the checkout process 

    Examples: 
      | username        | password | skip_list_item_click | skip_radio_button_click |
      #| user@gmail.com  | 1111     | false                | false                   |
      #| thanh@gmail.com | 1111     | true                 | true                    |
      | admin@gmail.com | 1111     | true                 | false                   |
      | thao@gmail.com  | 1111     | false                | true                    |












#Feature: Purchase Items
#
  #Scenario Outline: Purchase items from online store
    #Given browser is opening
    #And the user is on the login page
    #When user enter <username> and <password>
    #And the user adds random products to the cart
    #And the user proceeds to checkout
    #And user enters info
    #Then the user completes the checkout process
#
    #Examples: 
      #| username        | password |
      #| user@gmail.com  |     1111 |
      #| admin@gmail.com |     1111 |
      #| admin@gmail.com |     1111 |
      #| admin@gmail.com |     1111 |
      #| thao@gmail.com  |     1111 |
      #| thanh@gmail.com |     1111 |
      
      
      
    #Examples: 
      #| username       | password | Firstname | Lastname | email          | phone       | address               |
      #| user@gmail.com |     1111 | Thanh     | Dang     | user@gmail.com | 01234567890 | 123 Main Street, City |
    #| admin@gmail.com |     1111 | Jane      | Smith    | jane@example.com | 9876543210 | 456 Oak Street, Town  |
    #Then the user completes the checkout process
    #Examples: 
      #| Firstname | Lastname | username       | password | email          | phone      | address               |
      #| Thanh     | Hi       | user@gmail.com |     1111 | user@gmail.com | 1234567890 | 123 Main Street, City |
