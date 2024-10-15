Feature: Mercedes-Benz Shop interaction

Scenario: Filter cars and submit invalid contact details
  Given I open the Mercedes-Benz Shop used cars in Australia
    And I fill in the location details with:
      | State       | New South Wales |
      | Postal Code | 2007            |
      | Purpose     | Private         |
  When I click the filter button
    And I apply the following filters:
      | Color  | Red    |
  Then I sort by the most expensive cars first
      | Sorting | Price: High to Low |
  When I navigate to Vehicle Details page
  Then I can save the following vehicle details to a file
      #| Model Year    | 2018      |
      #| VIN Number    | 12345790  |
  When I click 'Enquire Now'
