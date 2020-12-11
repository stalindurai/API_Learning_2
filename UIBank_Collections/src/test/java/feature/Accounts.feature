Feature: Create a new Account

  Scenario: Create a new banking account
    Given env is set
    When POST request is made
    Then response code is 200

  Scenario: Apply for a Loan with created acct number
    Given env is set
    When POST request for new loan is made
    Then response code is 200
    And verify loan details

  Scenario: Transfer Funds
    Given env is set
    When POST request for transfer is made
    Then response code is 200
    And verify balance in from account

  Scenario: Get Account Details
    Given env is set
    When GET request is made
    Then response code is 200

