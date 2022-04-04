#Vinn (JHM)
Feature: Register activities
  Description: The workers can register for activities such as sick days or vacation
  Actors: Project manager, worker

  #Make assertions on event and what activity it is related to
Scenario: worker registers vacation/sick days
  Given a project with number 22001 and name "Project 1" exists
  Given there exists a worker with initials "JHM" from system database
  When the worker "JHM" schedules vacation from "DATE1" to "DATE1" with name "Vacation JHM" to project 22001
  And the project manager of 22001 accepts the worker "JHM"'s vacation request of name "Vacation JHM"
  Then the activity with name "Vacation JHM" is contained in project 22001's list of activities


Scenario: worker registers vacation for a negative duration
  Given a project with number 22001 and name "Project 1" exists
  Given there exists a worker with initials "JHM" from system database
  When the worker "JHM" schedules vacation from "DATE1" to "DATE1" with name "Vacation JHM" to project 22001
  And "DATE1" is after "DATE2"
  Then the error message "Cant have vacation for negative duration" is given