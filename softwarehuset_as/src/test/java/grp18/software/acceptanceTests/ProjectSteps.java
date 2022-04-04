package grp18.software.acceptanceTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ProjectSteps {

    @Given("a project with number {int} and name {string} exists")
    public void a_project_with_number_and_name_exists(int projectNumber, String projectName) throws Exception{

        project = new Project(projectName);

    }

    @When("the project manager of project {int} adds an activity with name {string} to project {int}")
    public void the_project_manager_of_project_adds_an_activity_with_name_to_project(int projectNumber, String activityName, int projectNumber2) {
        // activity eller activity name som arg?
        activity = new Activity(activityName);
        //getProject(projectNumber2).addActivity(activity);
        project.addActivity(activity);
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity with name {string} is contained in project {int}'s list of activities")
    public void the_activity_with_name_is_contained_in_project_s_list_of_activities(String activityName, int projectNumber) {
        // Either =
        assertTrue(activity.getName == activityName);
        assertTrue(project.getNumber == projectNumber);
        assertTrue(project.contains(activity));
        // Or
        //Incorporate project number
        p = getProjectList().stream().filter(x -> x.getID.equals(projectNumber)).findFirst().orElse(null);
        //assertTrue(p.getActivityList().stream().map(x-> x.getName()).collect(Collectors.toList()).contains(activityName));
        assertTrue(p.getActivityList().stream().map(x-> x.getName()).contains(activityName));

        throw new io.cucumber.java.PendingException();
    }

    @Given("the worker {string} has been assigned project manager of project {int}")
    public void the_worker_has_been_assigned_project_manager_of_project(String workerInitials, int projectNumber) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the activity with name {string} is contained in project {int}'s list of activities")
    public void given_the_activity_with_name_is_contained_in_project_s_list_of_activities(String workerInitials, int projectNumber) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the project manager of project {int} assigns the worker {string} to {string} of project {int}")
    public void the_project_manager_of_project_assigns_the_worker_to_of_project(int projectNumber, String workerInitials, String activityName, int projectNumber2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the worker {string} is working on {string}")
    public void the_worker_is_working_on(String workerInitials, String activityName) {
        Worker worker = workerList.stream().filter(x -> x.getInitials.equals(workerInitials)).findFirst().orElse(null);
        assertTrue(worker.getActivityList.stream.map(x -> x.getName()).contains(activityName));
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the project {int} does not have the worker {string} in its worker list")
    public void the_project_does_not_have_the_worker_in_its_worker_list(int projectNumber, String workerInitials) {
        //p = getProjectList().stream().filter(x -> x.getID.equals(projectNumber)).findFirst().orElse(null);
        Project project = getProject(projectNumber);
        //Worker worker = workerList.stream().filter(x -> x.getInitials.equals(workerInitials)).findFirst().orElse(null);
        Worker worker = getWorker(workerInitials);
        assertFalse(project.getWorkerList.stream.contains(worker));

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the project manager of project {int} assigns the worker {string} to project {int}")
    public void the_project_manager_of_project_assigns_the_worker_to_project(int projectNumber, String workerInitials, int projectNumber2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
