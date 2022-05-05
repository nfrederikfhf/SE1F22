package grp18.software.acceptanceTests;

import grp18.software.app.IllegalDateException;
import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {

    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;
    private Project project;

    public ProjectSteps(RegistrationApp RApp, ErrorMessageHolder errorMessage) {
        this.RApp = RApp;
        this.errorMessage = errorMessage;
    }
    @When("a project with name {string} is created in the system")
    public void a_project_with_name_is_created(String projectName) throws IllegalDateException{
        project = new Project(projectName);
        RApp.addProject(project);
    }

    @When("a project with name {string} from date {string} to {string} is created in the system")
    public void a_project_with_name_from_date_to_is_created_in_the_system(String projectName, String date1, String date2) {
        try {
            project = new Project(projectName,date1,date2);
            RApp.addProject(project);
        }catch (IllegalDateException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("a project with name {string} and ID {int} exists in the system")
    public void a_project_with_name_and_id_exists_in_the_system(String projectName, Integer projectID) {
        assertEquals(RApp.getProjectFromID(projectID).getName(), projectName);
    }

    @When("the project with ID {int} is being renamed to {string}")
    public void the_project_with_id_is_being_renamed_to(Integer projectID, String projectName) {
       RApp.getProjectFromID(projectID).setName(projectName);
    }


    @Given("a project with number {int} and name {string} exists")
    public void a_project_with_number_and_name_exists(int projectNumber, String projectName) throws IllegalDateException {
        project = new Project(projectName);
        RApp.addProject(project);

    }

    @When("the project manager of project {int} adds an activity with name {string} with timeframe {string} to {string} to project {int}")
    public void the_project_manager_of_project_adds_an_activity_with_name_to_project(int projectNumber, String activityName, String startDate, String endDate, int projectNumber2) {
        // activity eller activity name som arg?
        try{
            Activity activity = new Activity(activityName, startDate, endDate);
            try {
                RApp.getProjectFromID(projectNumber2).addActivity(activity);
            } catch (OperationNotAllowedException e) {
                errorMessage.setErrorMessage(e.getMessage());
            }
        } catch (IllegalDateException e){
            errorMessage.setErrorMessage(e.getMessage());
        }

    }

    @Then("the activity with name {string} is contained in project {int}'s list of activities")
    public void the_activity_with_name_is_contained_in_project_s_list_of_activities(String activityName, int projectNumber) {
        project = RApp.getProjectFromID(projectNumber);
        assertEquals(project.getActivityFromName(activityName).getActivityName(), activityName);
    }

    @Given("an activity with name {string} is contained in project {int}'s list of activities")
    public void given_the_activity_with_name_is_contained_in_project_s_list_of_activities(String activityName, int projectNumber) throws IllegalDateException {

            Activity activity = new Activity(activityName, "2,2,2022", "2,3,2022");
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

    @Then("the size of projects available is {int}")
    public void the_size_of_projects(int arraySize){
        assertEquals(arraySize,RApp.getProjects().size());
    }

}
