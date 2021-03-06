#Jacob Hellum
Feature: Add a worker to an activity
    Description A worker is added to an activity in a project
    Actors project manager

Scenario: The project manager adds a worker to an activity in a project
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And a worker with initials "JHM" has been assigned project manager of project 22001
    And there exists a worker with initials "NFF" from system database
    And the worker "NFF" is working on 0 activities
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager of project 22001 assigns the worker "NFF" to "Activity 1" of project 22001
    Then the worker "NFF" is working on "Activity 1"
    And the project 22001 does not have the worker "NFF" in its worker list

Scenario: The project manager tries to add a worker with 20 activities to another activity
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And a worker with initials "JHM" has been assigned project manager of project 22001
    And there exists a worker with initials "NFF" from system database
    And the worker "NFF" is working on 20 activities
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager of project 22001 assigns the worker "NFF" to "Activity 1" of project 22001
    Then worker "NFF" is working on 21 activities

Scenario: The project manager adds worker to project
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And a worker with initials "JHM" has been assigned project manager of project 22001
    And there exists a worker with initials "NFF" from system database
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager of project 22001 assigns the worker "NFF" to project 22001
    Then the worker "NFF" is working on project 22001
    When the project manager of project 22001 assigns the worker "NFF" to project 22001
    Then the error message "Worker already in project" is given


Scenario: The project manager adds the same worker twice to project
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And a worker with initials "JHM" has been assigned project manager of project 22001
    And there exists a worker with initials "NFF" from system database
    And an activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager of project 22001 assigns the worker "NFF" to project 22001
    Then the worker "NFF" is working on project 22001
    And the worker "NFF" is not working on activity with name "Activity 1" in project 22001

Scenario: Workers are added to the systems database
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    And there exists a worker with initials "NFF" from system database
    And there exists a worker with initials "WM" from system database
    Then the size of workers in the project is 3

