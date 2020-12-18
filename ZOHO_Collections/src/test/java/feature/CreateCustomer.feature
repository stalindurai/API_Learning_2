Feature: Customers
  Scenario Outline: Create a new customer
    Given env is set
    When POST for Create Customer is made
    Then response is "<code>"
    Examples:
    |code|
    |201 |

   Scenario Outline: Update an existing customer
     Given env is set
     When PUT for a specific Customer is made
     Then response is "<code>"
     Examples:
     |code|
     |200 |

  Scenario Outline: Get a specific customer
    Given env is set
    When GET for a Customer is made
    Then response is "<code>"
    Examples:
      |code|
      |200 |
