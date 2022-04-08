package grp18.software.acceptanceTests;

import grp18.software.app.EventOverlapException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;
public class EventSteps {

    private RegistrationApp registrationApp;
    private Worker worker;
    private Event event1;

    public EventSteps(RegistrationApp registrationApp, Worker worker){
        this.registrationApp = registrationApp;
        this.worker = worker;
    }



    @Given("the worker {string}'s eventlist is empty")
    public void the_worker_s_eventlist_is_empty(String workerName) {
        worker = new Worker(workerName);
    }

    @When("the worker {string} registers working hours from {string} to {string} on date {string} with ID {int} related to activity {string} of project {int}")
    public void the_worker_registers_working_hours_from_to_on_date_with_id_related_to_activity_to_project(String workerInitials, String startTime, String endTime, String date, int eventID, String activityName, int projectID) {
        StringToCalender dateData = new StringToCalender(startTime, endTime, date);
        Activity activity1 = registrationApp.getProjectFromID(projectID).getActivityFromName(activityName);
        event1 = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        try {
            worker.registerHours(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1);
        } catch (EventOverlapException e) {
            System.out.println("Events are overlapping");
        }
    }

    @Then("an event labeled {string} with ID {int} related to activity {string} is added to the worker {string}'s eventlist")
    public void an_event_labeled_with_id_related_to_activity_is_added_to_the_worker_s_eventlist(String string, String string2, String string3, String string4) {
        assert worker.getEventFromID(1)==event1;
    }

    @Given("the worker {string} has an event from {string} to {string} on date {string} with ID {int} related to activty with name {string}")
    public void the_worker_has_an_event_from_to_on_date_with_id(String workerName, String startTime, String endTime, String date, int ID, String activityName) {
        StringToCalender dateData = new StringToCalender(startTime, endTime, date);
        Activity activity1 = registrationApp.getProjectFromID(projectID).getActivityFromName(activityName);
        worker.registerHours(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1);
    }


    @Then("the event labeled {string} from {string} to {string} on date {string} with ID {string} is not registered to the worker {string}'s eventlist")
    public void the_event_labeled_from_to_on_date_with_id_is_not_registered_to_the_worker_s_eventlist(String string, String string2, String string3, String string4, String string5, String string6) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the worker changes the timeframe of the event with ID {string} to {string} to {string}")
    public void the_worker_changes_the_timeframe_of_the_event_with_id_to_to(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the event with ID {string} of worker {string} has timeframe {string} to {string}")
    public void the_event_with_id_of_worker_has_timeframe_to(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("The timeframe of the event with ID {string} is not changed")
    public void the_timeframe_of_the_event_with_id_is_not_changed(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
