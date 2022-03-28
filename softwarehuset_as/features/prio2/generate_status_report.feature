#William (JHM)
Feature: A status report is generated
    Description: The project manager generates a status report.
    Actor: Project manager

    #Make report more specific and assert on specific report content
Scenario: The project manager generates a report for an existing activity
    Given a project with number "22001" and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project "22001"'s list of activities
    When a status report is generated for the activity with name "Activity 1"
    Then a report is generated, with the amount of workers assigned to the activity, and the total work hours

    #Assert on report content
Scenario: The project manager generates a report for an existing activity, with no workers assigned.
    Given a project with number "22001" and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project "22001"'s list of activities
    When a report is generated for the activity with name "Activity 1"
    Then the error message "The following report, is empty as no workers are assigned to the currently selected activity." is given

Scenario: The project manager generates a report for the project
    Given a project with number "22001" and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project "22001"'s list of activities
    And the activity with name "Activity 2" is contained in project "22001"'s list of activities
    When a report is generated for project "22001"
    Then a report is generated, with the amount of workers assigned to the project, and the total work hours
    And a report is generated, with the amount of workers assigned to "Activity 1", and the total work hours
    And a report is generated, with the amount of workers assigned to "Activity 2", and the total work hours

    #Use this syntax for multiple workers
    #And these books are contained in the library
    #    | Extreme Programming | Kent Beck | Beck99 |
    #    | Test Driven Development | Kent Beck | Beck02 |
    #    | Lean Software Development | Mary Poppendieck and Tom Poppendieck | Pop07 |
    #    | Cucumber for Java | Seb Rose | Rose11 |