#Jacob Hellum
Feature: Add an activity to a project
    Description The project manager adds an activity to a project
    Actors Project manager

Scenario: An activity is added to a project
    Given A project with number "22001" and name "Project 1" exists
    And   A worker with initials "JHM" has been assigned project manager of project "22001"
    When  The project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    Then  The activity with name "Activity 1" is contained in project "22001"'s list of activities

Scenario: It is tried to add two activities with same name to a project
    Given A project with number "22001" and name "Project 1" exists
    And   A worker with initials "JHM" has been assigned project manager of project "22001"
    When  The project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    Then  The activity with name "Activity 1" is contained in project "22001"'s list of activities
    When  The project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    Then  The error message "Name for activity already used in the project" is given


