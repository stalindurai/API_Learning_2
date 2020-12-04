Feature: Create and List Channels

  Scenario Outline: Create Channel
    Given env is set
    When POST for channel is made
    Then response code is "<code>"
    Examples:
    |code|
    |201 |

  Scenario Outline: List Channels
    Given env is set
    When GET is made
    Then response code is "<code>"
    Examples:
    |code|
    |200 |
