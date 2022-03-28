#Niels

Feature: Edit event
Description: A worker wants to change timeframe of an event
Actor: A worker

Scenario: A worker wants to change the timeframe of an event, and there is no overlap with new timeframe.
    Given there exists a worker with initials "JHM" from system database
    And the worker "JHM" has an event from "12:00" to "14:00" on date "2022,2,5" with ID "000001"
    And the worker "JHM" has an event from "8:00" to "10:00" on date "2022,2,5" with ID "000002"
    When the worker changes the timeframe of the event with ID "000002" to "8:00" to "11:00"
    Then the event with ID "000002" of worker "JHM" has timeframe "8:00" to "11:00"

Scenario: A worker wants to change the timeframe of an event,
but there is an overlap in timeframe with another event
    Given there exists a worker with initials "JHM" from system database
    And the worker "JHM" has an event from "12:00" to "14:00" on date "2022,2,5" with ID "000001"
    And the worker "JHM" has an event from "8:00" to "10:00" on date "2022,2,5" with ID "000002"
    When the worker changes the timeframe of the event with ID "000002" to "8:00" to "13:00"
    Then The timeframe of the event with ID "000002" is not changed
    And the error message "Event is overlapping another event" is given

Scenario: A worker wants to change the timeframe of an event,
but there is an overlap in timeframe with another event at the limit of a timeframe
    Given there exists a worker with initials "JHM" from system database
    And the worker "JHM" has an event from "12:00" to "14:00" on date "2022,2,5" with ID "000001"
    And the worker "JHM" has an event from "8:00" to "10:00" on date "2022,2,5" with ID "000002"
    When the worker changes the timeframe of the event with ID "000002" to "8:00" to "12:00"
    Then The timeframe of the event with ID "000002" is not changed
    And the error message "Event is overlapping another event" is given

Scenario: Change date of event with no overlap

Scenario: Change date of event with overlap