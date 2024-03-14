@calc_sample
Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 4 and 5
    Then the result is 9

  Scenario: Substraction
    When I substract 7 to 2
    Then the result is 5

  Scenario: Multiplication
    When I multiplicate 2 and 5
    Then the result is 10

  Scenario: Division
    When I divide 8 by 4
    Then the result is 2

  Scenario: Invalid operation
    When I divide 8 by 0
    Then an error occurred

  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 1 | 2 | 3  |
      | 3 | 7 | 10 |

  Scenario Outline: Several subtractions
    When I substract <a> to <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c |
      | 2 | 1 | 1 |
      | 8 | 2 | 6 |

  Scenario Outline: Several multiplications
    When I multiplicate <a> and <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c  |
      | 2 | 1 | 2  |
      | 8 | 2 | 16 |

  Scenario Outline: Several divisions
    When I divide <a> by <b>
    Then the result is <c>

    Examples: Single digits
      | a | b | c |
      | 6 | 2 | 3 |
      | 8 | 4 | 2 |