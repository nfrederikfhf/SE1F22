#William (JHM)
Feature: An activity is edited
    Description The project manager edits an activity
    Actor Project manager

Scenario: The project manager edits an existing activity
    Given A project with number "22001" and name "Project 1" exists
    And The activity with name "Activity 1" is contained in project "22001"'s list of activities
    When The activity with name "Activity 1" is edited with a new name "Activity 2"
    Then The activity with name "Activity 2" is contained in project "22001"'s list of activities
    And  The activity with name "Activity 1" is not contained in project "22001"'s list of activities

Scenario: The project manager edits a non-existing activity
    Given A project with number "22001" and name "Project 1" exists
    And The project does not have an activity with name "Activity 1".
    When The activity with name "Activity 1" is edited with a new name "Activity 2"
    Then The error "No activity with that name is found, and it is unable to be edited" is returned.