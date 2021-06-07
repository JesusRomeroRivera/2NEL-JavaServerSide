Feature: investor feature

  Scenario: Get All Investors returns all investors
    Given the following investors
      | id | firstName | lastName | portfolio               |
      | 1  | jhon      | doe      | https://www.example.com |
      | 2  | mary      | smith    | https://www.example.com |
      | 3  | peter     | vega     | https://www.example.com |
    When the user requests all the investors
    Then all the investors are returned