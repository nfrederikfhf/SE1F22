#Niels
Feature: Register time spent working using event
    Description A worker wants to register time spent on a activity
    Actor A worker

Scenario: A worker adds an event to register hours
    Given there exists a worker with initials "JHM" from system database
    And a project with number "22001" and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project "22001"'s list of activities
    And there exists an activity with title "Make cucumber files"
    And the worker "JHM"'s eventlist does not have any events from "08:00" to "17:00" on date "2022,2,5"
    When the worker "JHM" registers working hours from "08:00" to "17:00" on date "2022,2,5" with ID "000001"
    Then an event labeled "work" with ID "000001" is added to the worker "JHM"'s eventlist



Scenario: A worker adds an event that overlaps with another events timeframe
    Given there exists a worker with initials "JHM" from system database
    And a project with number "22001" and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project "22001"'s list of activities
    And there exists an activity with title "Make cucumber files"
    And the worker "JHM" has an event from "12:00" to "14:00" on date "2022,2,5" with ID "000001"
    When the worker "JHM" registers working hours from "08:00" to "17:00" on date "2022,2,5" with ID "000002"
    Then the event labeled "work" from "08:00" to "17:00" on date "2022,2,5" with ID "000002" is not registered to the worker "JHM"'s eventlist
    And the error message "Event is overlapping another event" is given




  #Scenario: A worker adds an event that overlaps with another events timeframe\\
  #Given: there is a worker\\
  #And: there is an activity with title "Make cucumber files" and ID "220001"\\
  #When: a worker registers hours\\
  #And: the timeframe overlaps with another event\\
  #Then: the event is not registered\\
  #And: error message "Event is overlapping another event" is given\\ \\