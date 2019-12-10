@all
@trial

Feature: Check sikuli

  #Возможность использования sikuli для работы с картами
  @trial1
  Scenario Outline: Check sikuli
    When User logs in by major
    And User checks full map visibility
    And User checks map fragment <fullMap> visibility
    And User clicks by map fragment <mapFragment>

    Examples:
      | fullMap          | mapFragment         |
      | maps/fullMap.png | maps/zaoFullMap.png |