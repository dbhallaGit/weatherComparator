#Author: divesh.bhalla
#Git Repo:https://github.com/dbhallaGit/weatherComparator.git

Feature: Weather comparator
  To compare weather displayed on Web app is correct

  #@Smoke
  Scenario Outline: Compare weathers for multiple cities
    Given User is on "NDTV" website
    Given Navigate to weather page
    Then use pin location to select a city <CityName>
    Then verify same City appears on Map with temperature info

    Examples: 
      | CityName  |
      | New Delhi |
      | Srinagar  |
      | Alwar     |

  #@Smoke
  Scenario: : Compare weather displayed is correct
    Given Navigate to weather page
    Then use pin location to select a city "New Delhi"
    Then verify weather details reveals on selecting the city
    Then compare the Weather information from website and API response is similar as per given variance
