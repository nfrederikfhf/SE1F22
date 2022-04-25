package grp18.software.acceptanceTests;

import grp18.software.app.ActivityNotFoundException;
import grp18.software.domain.Worker;
import grp18.software.dto.ActivityInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ActivitySteps {
    private ActivityNotFoundException errorMessage;
    private ActivityInfo activity;

    public ActivitySteps(ActivityNotFoundException errorMessage, ActivityInfo activity){
        this.errorMessage = errorMessage;
        this.activity = activity;
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) throws Exception {
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    @When("the activity with name {string} is edited with a new name {string}")
    public void the_activity_with_name_titleis_edited_with_a_new_name(String initials, String initials) {
        activity = new ActivityInfo(initials);
        assertEquals(activity.getInitials(),initials);


    }

    @Then("the activity with name {string} is not contained in project {string}'s list of activities")
    public void the_activity_with_name_is_not_contained_in_project_s_list_of_activities(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project does not have an activity with name {string}")
    public void the_project_does_not_have_an_activity_with_name(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
