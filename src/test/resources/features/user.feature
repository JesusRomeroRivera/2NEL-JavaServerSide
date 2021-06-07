Feature: user feature

  Scenario: Get All Users returns all users
    Given the following users
      | id | email         | password |
      | 1  | a@example.com | b        |
      | 2  | b@example.com | c        |
      | 3  | c@example.com | d        |
    When the user requests all the users
    Then all the users are returned