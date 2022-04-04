#Vinn

Feature: Edit project
  Description: A project manager can request a change in a project
  Actors: Project manager, project

  Scenario: Project manager request to edit a project name
    Given there exists a project manager with initials "VINN" from system database
    And a project with number 22001 and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager changes project name with ID 22001 from "Project 1" to "Project 3"
    Then the project manager "VINN" has changed the project name with ID 22001 from "Project 1" to "Project 3"


  Scenario: Project manager request to edit a project name with overlap
    Given there exists a project manager with initials "VINN" from system database
    And a project with number 22001 and name "Project 1" exists
    And the activity with name "Activity 1" is contained in project 22001's list of activities
    When the project manager changes project name with ID 22001 from "Project 1" to "Project 3"
    Then the error message "Project name is overlapping another project name" is given