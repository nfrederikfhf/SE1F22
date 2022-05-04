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


    #Assert on report content
#Scenario: The project manager generates a report for an existing activity, with no workers assigned.
#    Given a project with number 22001 and name "Project 1" exists
#    And the activity with name "Activity 1" is contained in project 22001's list of activities
#    When a report is generated for the activity with name "Activity 1"
#    Then the error message "The following report, is empty as no workers are assigned to the currently selected activity." is given
#
#Scenario: The project manager generates a report for the project
#    Given a project with number 22001 and name "Project 1" exists
#    And the activity with name "Activity 1" is contained in project 22001's list of activities
#    And the activity with name "Activity 2" is contained in project 22001's list of activities
#    When a report is generated for project 22001
#    Then a report is generated, with the amount of workers assigned to the project, and the total work hours
#    And a report is generated, with the amount of workers assigned to "Activity 1", and the total work hours
#    And a report is generated, with the amount of workers assigned to "Activity 2", and the total work hours
#
    #Use this syntax for multiple workers
    #And these books are contained in the library
    #    | Extreme Programming | Kent Beck | Beck99 |
    #    | Test Driven Development | Kent Beck | Beck02 |
    #    | Lean Software Development | Mary Poppendieck and Tom Poppendieck | Pop07 |
    #    | Cucumber for Java | Seb Rose | Rose11 |