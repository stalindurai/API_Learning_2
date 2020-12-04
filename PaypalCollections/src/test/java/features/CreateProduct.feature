Feature: Create Product

  Background:
#    Given env is set

  Scenario Outline: Create a Sample Product

    When POST request is made "<json>"
    Then product is created
    Then status code is 200
    And response type is JSON
    Examples:
    |json|
    |src/test/java/body.json|
    |src/test/java/body.json|
    |src/test/java/body.json|

