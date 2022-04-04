package grp18.software.acceptanceTests;

import grp18.software.app.RegistrationApp;
import grp18.software.domain.Worker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
public class WorkerSteps {
    private RegistrationApp rAPP;
    private Worker worker;

    public WorkerSteps(RegistrationApp rAPP){
        this.rAPP = rAPP;
    }


    @Given("a worker with initials {string} has been assigned project manager of project {int}")
    public void a_worker_with_initials_has_been_assigned_project_manager_of_project(String initials, int projectID) {
        worker = new Worker(initials);
        rAPP.getProjectFromID(projectID).setManager(worker);
        worker.setProjectManager(true);
    }

    @Given("the worker {string} is working on {int} activities")
    public void the_worker_is_working_on_activities(String initials, int activityCount) {
        worker = new Worker(initials);




        activities = worker.getAmountOfActivities();
        assertEquals(activityCount,activities);
    }

    @Given("there exists a worker with initials {string} from system database")
    public void there_exists_a_worker_with_initials_from_system_database(String initials) {
        worker = new Worker(initials);

        assertEquals(worker.getInitials(), initials);
    }

    @Then("the worker {string} is working on project {string}")
    public void the_worker_is_working_on_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the worker {string} is not working on activity with name {string} in project {string}")
    public void the_worker_is_not_working_on_activity_with_name_in_project(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
