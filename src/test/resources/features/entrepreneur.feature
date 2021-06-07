Feature: entrepreneur feature

  Scenario: Get All Entrepreneurs returns all entrepreneurs
    Given the following entrepreneurs
      | id | firstName | lastName | portfolio       |
      | 1  | jhon      | doe      | www.example.com |
      | 2  | mary      | smith    | www.example.com |
      | 3  | peter     | vega     | www.example.com |
    When the user requests all the entrepreneurs
    Then all the entrepreneurs are returned