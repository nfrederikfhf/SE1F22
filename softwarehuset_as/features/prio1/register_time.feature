#Niels
Feature: Register time spent working using event
    Description A worker wants to register time spent on a activity
    Actor A worker

Scenario: A worker adds an event to register hours
    Given there exists a worker with initials "JHM" from system database
    And a project with number 22001 and name "Project 1" exists
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    Then the worker "JHM"'s eventlist is empty
    When the worker "JHM" registers working hours from "8,00" to "17,00" on date "2022,02,05" with ID 1 related to activity "Activity 1" of project 22001
    Then an event labeled "work" with ID 1 related to activity "Activity 1" is added to the worker "JHM"'s eventlist



Scenario: A worker adds an event that overlaps with another events timeframe
    Given there exists a worker with initials "JHM" from system database
    And a project with number 22001 and name "Project 1" exists
    And the worker "JHM" has an event from "12,00" to "14,00" on date "2022,02,05" with ID 1 related to activity with name "Activity 1" in project with ID 22001
    When the worker "JHM" registers working hours from "08,00" to "17,00" on date "2022,02,05" with ID 2 related to activity "Activity 1" of project 22001
    Then the event labeled "work" from "08,00" to "17,00" on date "2022,02,05" with ID 2 is not registered to the worker "JHM"'s eventlist
    And the error message "Event is overlapping another event" is given


Scenario: A worker registers time with incorrect timeformats
    Given there exists a worker with initials "JHM" from system database
    And a project with number 22001 and name "Project 1" exists
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    Then the worker "JHM"'s eventlist is empty
    When the worker "JHM" registers working hours from "8,00,00" to "19,00,30" on date "02,05" with ID 1 related to activity "Activity 1" of project 22001
    Then the error message "Date is invalid" is given



  #Scenario: A worker adds an event that overlaps with another events timeframe\\
  #Given: there is a worker\\
  #And: there is an activity with title "Make cucumber files" and ID "220001"\\
  #When: a worker registers hours\\
  #And: the timeframe overlaps with another event\\
  #Then: the event is not registered\\
  #And: error message "Event is overlapping another event" is given\\ \\