Feature: All Time Entries
#  Scenario Outline: ; Display all Time Entries available
#    Given env is set
#    When GET request for all Time Entries
#    Then response code is "<code>"
#    Examples:
#    |code|
#    |200 |

#  Scenario Outline: Create a Single Client
#      Given env is set
#      When POST request for Client is made
#      Then response code is "<code>"
#    Examples:
#      |code|
#      |200 |

#  Scenario Outline: Create a Single Project
#      Given env is set
#      When POST request for Projects is made
#      Then response code is "<code>"
#    Examples:
#      |code|
#      |200 |
#
  Scenario Outline: Create a Time Entry
      Given env is set
      When POST request for Time Entry is made
      Then response code is "<code>"
    Examples:
      |code|
      |200 |