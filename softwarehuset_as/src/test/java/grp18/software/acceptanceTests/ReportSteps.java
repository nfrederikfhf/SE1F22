package grp18.software.acceptanceTests;

import grp18.software.app.RegistrationApp;
import grp18.software.domain.Worker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//Jacob
public class ReportSteps {
    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;

    public ReportSteps(RegistrationApp RApp, ErrorMessageHolder errorMessage) {
        this.errorMessage = errorMessage;
        this.RApp = RApp;
    }


    @Given("these workers exists in the system data base")
    public void these_workers_exists_in_the_system_data_base(List<String> names) {
        for (String name : names){
            RApp.addWorker(new Worker(name));
        }
    }
    @When("a status report is generated for the system")
    public void a_status_report_is_generated_for_the_system() {
        StringBuilder stringBuilder = new StringBuilder("");
        RApp.getStatusReport(stringBuilder);
        //RApp.getProjectFromID(22001).printStatusReport("",stringBuilder);
        //RApp.getProjectFromID(22001).getActivityFromName("Activity 1").printStatusReport("   ",stringBuilder);
        System.out.println(stringBuilder);
        //assertEquals("ff","ff");
    }

    @Then("the system status report has the following content and structure:")
    public void the_status_report_has_the_following_content_and_structure(List<String> content) {
        StringBuilder stringBuilder = new StringBuilder("");
        RApp.getStatusReport(stringBuilder);
        String[] report = stringBuilder.toString().split("\\n");
        int i = 0;

        for (String line : report){

           assertTrue(line.contains(content.get(i)));
            i++;
        }

    }
    @Then("a status report is generated for project with ID {int}")
    public void a_status_report_is_generated_for_project_with_id(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the project status report of project {int} has the following content and structure:")
    public void the_project_status_report_of_project_has_the_following_content_and_structure(Integer projectID, List<String> content) {
        StringBuilder stringBuilder = new StringBuilder("");
        RApp.getProjectFromID(projectID).getStatusReport("",stringBuilder);
        String[] report = stringBuilder.toString().split("\\n");
        int i = 0;
        for (String line : report){
            assertTrue(line.contains(content.get(i)));
            i++;
        }
    }
}