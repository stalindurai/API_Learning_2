Feature: Regen Key
  Scenario Outline: Regenerate Admin Key
    Given env to regen admin key
    When post regen is made
    Then verify status "<code>"
    And verify response type
    Examples:
      |code|
      |200 |