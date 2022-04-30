#Jacob Hellum

Feature: Create a project
  Description: Creating a project in the system
  Actors: ???

  Scenario: Two projects is created
      When a project with name "Project 1" is created in the system
      And a project with name "Project 2" is created in the system
      Then a project with name "Project 1" and ID 22001 exists in the system
      And a project with name "Project 2" and ID 22002 exists in the system

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
