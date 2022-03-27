#Jacob Hellum
Feature: Add a worker to an activity
    Description A worker is added to an activity in a project
    Actors project manager

Scenario: The project manager adds a worker to an activity in a project
    Given a project with number "22001" and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And the worker "JHM" has been assigned project manager of project "22001"
    And there exists a worker with initials "NFF" from system database
    And the worker "NFF" is working on "0" activities
    When the project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    And the project manager of project "22001" assigns the worker "NFF" to "Activity 1" of project "22001"
    Then the worker "NFF" has thte activity "Activity 1" in his/her activity list
    And the activity "Activity 1" has the worker "NFF" in its worker list
    And the project "22001" does not have the worker "NFF" in its worker list

Scenario: The project manager tries to add a worker with 20 activities to another activity
    Given a project with number "22001" and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And the worker "JHM" has been assigned project manager of project "22001"
    And there exists a worker with initials "NFF" from system database
    And the worker "NFF" is working on "20" activities during some time in the duration of the activity
    When the project manager of project "22001" adds an activity with name "Activity 1" to project "22001"
    And the project manager of project "22001" assigns the worker "NFF" to "Activity 1" of project "22001"
    Then the error message "The worker cannot work on more than 20 activities at the same time" given
