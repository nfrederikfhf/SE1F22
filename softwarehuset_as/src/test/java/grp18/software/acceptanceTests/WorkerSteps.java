package grp18.software.acceptanceTests;

import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class WorkerSteps {
    private RegistrationApp RApp;

    public WorkerSteps(RegistrationApp RApp) {
        this.RApp = RApp;
    }


    @Given("a worker with initials {string} has been assigned project manager of project {int}")
    public void a_worker_with_initials_has_been_assigned_project_manager_of_project(String initials, int projectID) {
        // Assigns the project manager
        RApp.getProjectFromID(projectID).assignManager(RApp.getWorkerFromInitials(initials));
        RApp.getWorkerFromInitials(initials).setProjectManager(true);
    }

    @Given("the worker {string} is working on {int} activities")
    public void the_worker_is_working_on_activities(String initials, int activityCount) {
        // Checks count of current activites worked on
        StringToCalender startDatedata = new StringToCalender("0,0,0", "0,0,0", "2,2,2022");
        StringToCalender endDatedata = new StringToCalender("0,0,0", "0,0,0", "2,3,2022");
        for (int i = 0; i<activityCount; i++){
            RApp.getWorkerFromInitials(initials).addActivity(new Activity("DummyActivity"+i,
                    startDatedata.dateCal,endDatedata.dateCal));
        }
    }

    @Given("there exists a worker with initials {string} from system database")
    public void there_exists_a_worker_with_initials_from_system_database(String initials) {
        // Checks if the worker exists
        RApp.addWorker(new Worker(initials));
    }

    @Then("the worker {string} is working on project {int}")
    public void the_worker_is_working_on_project(String workerInitials, int projectNumber) {
        assertTrue(RApp.getProjectFromID(projectNumber).getWorkers().contains(RApp.getWorkerFromInitials(workerInitials)));
    }

    @Then("the worker {string} is not working on activity with name {string} in project {int}")
    public void the_worker_is_not_working_on_activity_with_name_in_project(String workerInitials, String activityName, int projectNumber) {
        assertFalse(RApp.getProjectFromID(projectNumber).getActivityFromName(activityName).getWorkers().contains(RApp.getWorkerFromInitials(workerInitials)));
    }

    @Then("worker {string} is working on {int} activities")
    public void workerIsWorkingOnActivities(String workerInitials, int activityCount) {
        assertEquals(RApp.getWorkerFromInitials(workerInitials).getAmountOfActivities(), activityCount);
    }

    @When("the worker with initials {string} is being assigned project manager of project {int}")
    public void the_worker_with_initials_is_being_assigned_project_manager_of_project(String workerInitials, Integer projectID) {
        RApp.getProjectFromID(projectID).assignManager(RApp.getWorkerFromInitials(workerInitials));
        RApp.getWorkerFromInitials(workerInitials).setProjectManager(true);
    }
    @Then("the worker {string} is managing a project")
    public void the_worker_is_managing_project(String workerInitials) {
        assertTrue(RApp.getWorkerFromInitials(workerInitials).getProjectManager());
    }
}
