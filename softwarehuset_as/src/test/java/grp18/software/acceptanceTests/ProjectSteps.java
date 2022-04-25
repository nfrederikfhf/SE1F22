package grp18.software.acceptanceTests;

import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import grp18.software.tools.StringToCalender;

import javax.crypto.spec.OAEPParameterSpec;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {

    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;
    private Project project;

    public ProjectSteps(RegistrationApp RApp, ErrorMessageHolder errorMessage) {
        this.RApp = RApp;
        this.errorMessage = errorMessage;
    }

    @Given("a project with number {int} and name {string} exists")
    public void a_project_with_number_and_name_exists(int projectNumber, String projectName) throws Exception {
        project = new Project(projectName);
        RApp.addProject(project);
        assertEquals(project.getID(), projectNumber);
    }

    @When("the project manager of project {int} adds an activity with name {string} with timeframe {string} to {string} to project {int}")
    public void the_project_manager_of_project_adds_an_activity_with_name_to_project(int projectNumber, String activityName, String startDate, String endDate, int projectNumber2) {
        // activity eller activity name som arg?
        StringToCalender startDatedata = new StringToCalender(startDate,"0,0", "0,0");
        StringToCalender endDatedata = new StringToCalender( endDate,"0,0", "0,0");
        Activity activity = new Activity(activityName, startDatedata.dateCal, endDatedata.dateCal);
        try {
            RApp.getProjectFromID(projectNumber2).addActivity(activity);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        //   project.addActivity(activity);
        // throw new io.cucumber.java.PendingException();
    }

    @Then("the activity with name {string} is contained in project {int}'s list of activities")
    public void the_activity_with_name_is_contained_in_project_s_list_of_activities(String activityName, int projectNumber) {
        project = RApp.getProjectFromID(projectNumber);
        assertEquals(project.getActivityFromName(activityName).getActivityName(), activityName);
    }

    @Given("an activity with name {string} is contained in project {int}'s list of activities")
    public void given_the_activity_with_name_is_contained_in_project_s_list_of_activities(String activityName, int projectNumber) {
        StringToCalender startDatedata = new StringToCalender("0,0,0", "0,0,0", "2,2,2022");
        StringToCalender endDatedata = new StringToCalender("0,0,0", "0,0,0", "2,3,2022");
        Activity activity = new Activity(activityName, startDatedata.dateCal, endDatedata.dateCal);
        try {
            RApp.getProjectFromID(projectNumber).addActivity(activity);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the project manager of project {int} assigns the worker {string} to {string} of project {int}")
    public void the_project_manager_of_project_assigns_the_worker_to_of_project(int projectNumber, String workerInitials, String activityName, int projectNumber2) {
        project = RApp.getProjectFromID(projectNumber2);
        try {
            project.addWorkerToActivity(RApp.getWorkerFromInitials(workerInitials), project.getActivityFromName(activityName));
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the worker {string} is working on {string}")
    public void the_worker_is_working_on(String workerInitials, String activityName) {
        assertTrue(project.getActivityFromName(activityName).getWorkers().contains(RApp.getWorkerFromInitials(workerInitials)));
    }

    @Then("the project {int} does not have the worker {string} in its worker list")
    public void the_project_does_not_have_the_worker_in_its_worker_list(int projectNumber, String workerInitials) {
        assertFalse(project.getWorkers().contains(RApp.getWorkerFromInitials(workerInitials)));
    }

    @When("the project manager of project {int} assigns the worker {string} to project {int}")
    public void the_project_manager_of_project_assigns_the_worker_to_project(int projectNumber, String workerInitials, int projectNumber2) {
        try {
            RApp.getProjectFromID(projectNumber).addWorker(RApp.getWorkerFromInitials(workerInitials));
        } catch (OperationNotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

}
