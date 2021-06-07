Feature: freelancer feature

  Scenario: Get All Freelancers returns all freelancers
    Given the following freelancer
      | id | firstName | lastName | portfolio       | specialty |
      | 1  | jhon      | doe      | www.example.com | a         |
      | 2  | mary      | smith    | www.example.com | b         |
      | 3  | peter     | vega     | www.example.com | c         |
    When the user requests all the freelancers
    Then all the freelancers are returned