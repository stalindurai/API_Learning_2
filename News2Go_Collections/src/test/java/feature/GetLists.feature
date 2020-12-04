Feature: Get Lists
#  Scenario Outline: Create a new List
#    Given env is set
#    When POST list is made
#    Then status code is "<code>"
#    Examples:
#      |code|
#      |201 |

#  Scenario Outline: Get All available Lists
#    Given env is set
#    When get query is made
#    Then lists are displayed
#    And status code is "<code>"
#    Examples:
#    |code|
#    |200 |

  Scenario Outline: Create new Segment
    Given env is set
    When POST segment is made
    Then status code is "<code>"
    Examples:
    |code|
    |201 |

  Scenario Outline: Update a Segment
    Given env is set
    When PATCH segment is made
    Then status code is "<code>"
    Examples:
    |code|
    |201 |


#  Scenario Outline: Delete a List
#    Given env is set
#    When delete query is made
#    Then status code is "<code>"
#    Examples:
#    |code|
#    |204 |