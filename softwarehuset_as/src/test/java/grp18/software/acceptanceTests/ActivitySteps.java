package grp18.software.acceptanceTests;

import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.tools.StringToCalender;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivitySteps {
    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;

    public ActivitySteps(RegistrationApp RApp, ErrorMessageHolder errorMessage){
        this.errorMessage = errorMessage;
        this.RApp = RApp;
    }


    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    @When("the activity with name {string} is edited with a new name {string}")
    public void the_activity_with_name_is_edited_with_a_new_name(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity with name {string} is not contained in project {int}'s list of activities")
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
