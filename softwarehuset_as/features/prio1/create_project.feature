#Jacob Hellum

Feature: Create a project
  Description: Creating a project in the system
  Actors: ???

  Scenario: Two projects is created
      When a project with name "Project 1" from date "2020,01,01" to "2020,06,01" is created in the system
      And a project with name "Project 2" is created in the system
      Then a project with name "Project 1" and ID 22001 exists in the system
      And a project with name "Project 2" and ID 22002 exists in the system

  Scenario: Two projects is created
    When a project with name "Project 1" from date "2020,01,hello" to "hello" is created in the system
    Then the error message "Date is invalid" is given
    When a project with name "Project 1" from date "2020,01,01,01" to "2020,06,01" is created in the system
    Then the error message "Date is invalid" is given
    When a project with name "Project 1" from date "" to "2020,06,01" is created in the system
    Then the error message "Date is invalid" is given

  Scenario: A project is created and renamed
      When a project with name "Project 1" is created in the system
      Then a project with name "Project 1" and ID 22001 exists in the system
      When the project with ID 22001 is being renamed to "Project 1 new name"
      Then a project with name "Project 1 new name" and ID 22001 exists in the system

  Scenario: A project sets its  manager
      Given a project with number 22001 and name "Project 1" exists
      And there exists a worker with initials "JHM" from system database
      When the worker with initials "JHM" is being assigned project manager of project 22001
      Then the worker "JHM" is managing a project

  Scenario: A project sets its  manager with a non existent worker
    Given a project with number 22001 and name "Project 1" exists
    And there exists a worker with initials "JHM" from system database
    When the worker with initials "WHJHM" is being assigned project manager of project 22001
    Then the error message "Worker not found" is given


  Scenario: All added projects are shown
    When a project with name "Project 1" is created in the system
    When a project with name "Project 2" is created in the system
    When a project with name "Project 3" is created in the system
    When a project with name "Project 4" is created in the system
    Then the size of projects available is 4