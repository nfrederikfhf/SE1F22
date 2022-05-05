package grp18.software.acceptanceTests;

import grp18.software.app.ActivityNotFoundException;
import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Project;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ActivitySteps {
    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;
    private Project project;
    private Activity activity;

    public ActivitySteps(RegistrationApp RApp, ErrorMessageHolder errorMessage){
        this.errorMessage = errorMessage;
        this.RApp = RApp;
    }


    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    @When("the activity with name {string} in project {int} is edited with a new name {string}")
    public void the_activity_with_name_is_edited_with_a_new_name(String activityName, int projectID, String newActivityName) {
        RApp.getProjectFromID(projectID).getActivityFromName(activityName).setActivityName(newActivityName);
    }

    @When("activity {string} in project {int} is edited with a new name {string}")
    public void activity_is_edited_with_a_new_name(String activityName, int projectID, String newActivityName) {
        try {
            RApp.getProjectFromID(projectID).checkActivityName(activityName);
        } catch (ActivityNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the activity with name {string} is not contained in project {int}'s list of activities")
    public void the_activity_with_name_is_not_contained_in_project_s_list_of_activities(String activityName, int projectID) {
        try {
            RApp.getProjectFromID(projectID).checkActivityName(activityName);
        } catch (ActivityNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Given("the project with number {int} does not have an activity with name {string}")
    public void the_project_does_not_have_an_activity_with_name(int projectNumber,String activityName) {
        try {
            RApp.getProjectFromID(projectNumber).checkActivityName(activityName);
        } catch (ActivityNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the new name of {string} is {string} in project {int}")
    public void the_new_name(String oldActivityName,String activityName, int projectID) {
        try {
            activity = RApp.getProjectFromID(projectID).checkActivityName(activityName);
        } catch (ActivityNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertEquals(activityName, activity.getActivityName());
    }
}
