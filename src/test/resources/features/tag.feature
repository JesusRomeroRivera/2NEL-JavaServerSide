Feature: tag feature

  Scenario: Get All Tags returns all tags
    Given the following tags
      | id | name |
      | 1  | a    |
      | 2  | b    |
      | 3  | c    |
    When the user requests all tags
    Then all tags are returned