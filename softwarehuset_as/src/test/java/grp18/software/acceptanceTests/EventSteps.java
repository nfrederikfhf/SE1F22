package grp18.software.acceptanceTests;

import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventSteps {

    private RegistrationApp RApp;
    private Worker worker;
    private Event event1;
    private ErrorMessageHolder errorMessage;

    public EventSteps(RegistrationApp RApp, ErrorMessageHolder errorMessage){
        this.RApp = RApp;
        this.errorMessage = errorMessage;
    }

    @Then("the worker {string}'s eventlist is empty")
    public void the_worker_s_eventlist_is_empty(String workerInitials) {
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEvents().size(),0);
    }

    @When("the worker {string} registers working hours from {string} to {string} on date {string} with ID {int} related to activity {string} of project {int}")
    public void the_worker_registers_working_hours_from_to_on_date_with_id_related_to_activity_to_project(String workerInitials, String startTime, String endTime, String date, int eventID, String activityName, int projectID) {
            Activity activity1 = RApp.getProjectFromID(projectID).getActivityFromName(activityName);
            try {
                event1 = new Event(startTime, endTime,date, activity1, 1);
            }catch (IllegalDateException e){
                errorMessage.setErrorMessage(e.getMessage());
            }
            try {
                RApp.getWorkerFromInitials(workerInitials).registerHours(startTime, endTime, date, activity1);
            } catch (EventOverlapException e) {
                errorMessage.setErrorMessage(e.getMessage());
            }
    }

    @Then("an event labeled {string} with ID {int} related to activity {string} is added to the worker {string}'s eventlist")
    public void an_event_labeled_with_id_related_to_activity_is_added_to_the_worker_s_eventlist(String eventLabel, int eventID, String activityName, String workerInitials) {
        assertTrue(RApp.getWorkerFromInitials(workerInitials).getEvents().contains(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID)));
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getRelatedActivity().getActivityName(),activityName);
    }

    @Given("the worker {string} has an event from {string} to {string} on date {string} with ID {int} related to activity with name {string} in project with ID {int}")
    public void the_worker_has_an_event_from_to_on_date_with_id(String workerInitials, String startTime, String endTime, String date, int eventID, String activityName, int projectID) {
        Activity activity1 = RApp.getProjectFromID(projectID).getActivityFromName(activityName);
        try {
            RApp.getWorkerFromInitials(workerInitials).registerHours(startTime, endTime, date, activity1);
        } catch (EventOverlapException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }


    @Then("the event labeled {string} from {string} to {string} on date {string} with ID {int} is not registered to the worker {string}'s eventlist")
    public void the_event_labeled_from_to_on_date_with_id_is_not_registered_to_the_worker_s_eventlist(String eventName, String startTime, String endTime, String date, int eventID, String workerInitials) {
        Boolean boll = RApp.getWorkerFromInitials(workerInitials).getEvents().contains(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID));
        assertFalse(boll);
    }

    @When("the worker {string} changes the timeframe of the event with ID {int} to {string} to {string} with date {string}")
    public void the_worker_changes_the_timeframe_of_the_event_with_id_to_to(String workerInitials, int eventID, String newStartTime, String newEndTime, String newDate) {
        try {
            RApp.getWorkerFromInitials(workerInitials).editEvent(eventID, newStartTime, newEndTime, newDate);
        } catch (EventOverlapException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the event with ID {int} of worker {string} has timeframe {string} to {string} and date {string}")
    public void the_event_with_id_of_worker_has_timeframe_to(int eventID, String workerInitials, String startTime, String endTime, String date) throws IllegalDateException {
        StringToCalender dateData = null;
        try {
            dateData = new StringToCalender(date, startTime, endTime);
        } catch (IllegalDateException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getStartTime().getTimeInMillis(), dateData.startTimeCal.getTimeInMillis());
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getEndTime().getTimeInMillis(), dateData.endTimeCal.getTimeInMillis());
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getDate().getTimeInMillis(), dateData.dateCal.getTimeInMillis());

    }

    @Then("the worker {string} still has an event with ID {int} and timeframe {string} to {string} and date {string}")
    public void the_timeframe_of_the_event_with_id_is_not_changed(String workerInitials, int eventID, String startTime, String endTime, String date) throws IllegalDateException {
        StringToCalender dateData = new StringToCalender(date, startTime, endTime);
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getStartTime().getTimeInMillis(), dateData.startTimeCal.getTimeInMillis());
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getEndTime().getTimeInMillis(), dateData.endTimeCal.getTimeInMillis());
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getEventFromID(eventID).getDate().getTimeInMillis(), dateData.dateCal.getTimeInMillis());
    }


}
