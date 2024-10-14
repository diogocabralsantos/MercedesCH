Feature: Mercedes-Benz Shop interaction

Scenario: Filter cars and submit invalid contact details
Given I open the Mercedes-Benz Shop used cars in Australia
  And I fill in the location details with:
    | State       | New South Wales |
    | Postal Code | 2007            |
    #| Purpose     | Private         |
