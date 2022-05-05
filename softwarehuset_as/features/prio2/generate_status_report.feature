#JHM
Feature: A status report is generated
    Description: The project manager generates a status report.
    Actor: Project manager
Background: A system with workers, projects and activities exists
    Given a project with number 22001 and name "Project 1" exists
    And a project with number 22002 and name "Project 2" exists
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    And an activity with name "Activity 2" is contained in project 22001's list of activities
    And an activity with name "Activity 1" is contained in project 22002's list of activities
    And these workers exists in the system data base
        | JHM |
        | NFF |
        | WM |
    And a worker with initials "WM" has been assigned project manager of project 22001


    #Make report more specific and assert on specific report content
Scenario: The project manager generates a report for the system
    When the project manager of project 22001 assigns the worker "JHM" to "Activity 1" of project 22001
    And the worker "JHM" registers working hours from "8,00" to "17,00" on date "2022,02,05" with ID 1 related to activity "Activity 1" of project 22001
    And the worker "JHM" registers working hours from "8,00" to "15,00" on date "2022,02,06" with ID 2 related to activity "Activity 1" of project 22001
    And the project manager of project 22001 assigns the worker "NFF" to "Activity 1" of project 22001
    And the project manager of project 22001 assigns the worker "NFF" to "Activity 2" of project 22001
    And the worker "NFF" registers working hours from "8,00" to "17,00" on date "2022,02,05" with ID 1 related to activity "Activity 1" of project 22001
    And the project manager of project 22002 assigns the worker "NFF" to "Activity 1" of project 22002
    And the worker "NFF" registers working hours from "8,00" to "17,00" on date "2022,02,06" with ID 2 related to activity "Activity 1" of project 22002
    Then the system status report has the following content and structure:
        |Status report|
        |Project 1|
        |PM: WM|
        |Activity 1|
        |JHM|
        |Hours worked: 16|
        |NFF|
        |Hours worked: 9|
        |Activity 2|
        |NFF|
        |Hours worked: 0|
        |Project 2|
        |Activity 1|
        |NFF|
        |Hours worked: 9|

    And the project status report of project 22001 has the following content and structure:
        |Project 1|
        |PM: WM|
        |Activity 1|
        |JHM|
        |Hours worked: 16|
        |NFF|
        |Hours worked: 9|
        |Activity 2|
        |NFF|
        |Hours worked: 0|