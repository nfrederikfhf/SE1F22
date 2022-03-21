Feature: Add an activity to a project
    Description The project manager adds an activity to a project
    Actors Project manager

Scenario: An activity is added to a project
    Given A project with number "22001" and name "Project 1" exists
    And   A worker with initials "JHM" has been assigned project manager of project "22001"
    When  The project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    Then  The activity with name "Activity 1" is contained in project "22001"'s list of activities

Scenario: It is tried to add an activity to two projects
    Given Two project exists
    And   A worker has been assigned as project manager in both projects
    When  The project manager creates an activity
    And   The project manager adds the activity to one project
    Then  The activity is in the projects list of activities
    When  The project manager adds the activity to the second project
    Then  An error message given
