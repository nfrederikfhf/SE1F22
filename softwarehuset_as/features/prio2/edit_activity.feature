#William (JHM)
Feature: An activity is edited
    Description The project manager edits an activity
    Actor Project manager

Scenario: The project manager edits an existing activity
    Given a project with number 22001 and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project 22001's list of activities
    When the activity with name "Activity 1" is edited with a new name "Activity 2"
    Then the activity with name "Activity 2" is contained in project 22001's list of activities
    And  the activity with name "Activity 1" is not contained in project 22001's list of activities

Scenario: The project manager edits a non-existing activity
    Given a project with number 22001 and name "Project 1" exists
    And the project does not have an activity with name "Activity 1"
    When the activity with name "Activity 1" is edited with a new name "Activity 2"
    Then the error message "No activity with that name is found, and it is unable to be edited" is given