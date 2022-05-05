#William (JHM)
Feature: An activity is edited
    Description The project manager edits an activity
    Actor Project manager

Scenario: The project manager edits an existing activity and checks if the old one still exists
    Given a project with number 22001 and name "Project 1" exists
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the activity with name "Activity 1" in project 22001 is edited with a new name "Activity 2"
    Then the activity with name "Activity 2" is contained in project 22001's list of activities
    When the activity with name "Activity 1" is not contained in project 22001's list of activities
    Then the error message "Activity not found" is given


Scenario: The project manager edits an existing activity
    Given a project with number 22001 and name "Project 1" exists
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the activity with name "Activity 1" in project 22001 is edited with a new name "Activity 2"
    Then the new name of "Activity 1" is "Activity 2" in project 22001


Scenario: The project manager edits a non-existing activity
    Given a project with number 22001 and name "Project 1" exists
    And the project with number 22001 does not have an activity with name "Activity 1"
    When activity "Activity 1" in project 22001 is edited with a new name "Activity 2"
    Then the error message "Activity not found" is given

Scenario: The project manager tries to add an activity with illegal dates
    Given a project with number 22001 and name "Project 1" exists
    When an activity with name "Activity 1" from date "NOTADATE" to "2020,06,01" is added to project 22001
    Then the error message "Date is invalid" is given