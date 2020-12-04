Feature: Add a New TomTom Project
  Scenario Outline: New Project
    Given env is set
    When post request is made
    Then verify status "<code>"
    And verify response type
    Examples:
    |code|
    |201 |

