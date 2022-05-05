package grp18.software.acceptanceTests;

import grp18.software.app.IllegalDateException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import grp18.software.app.OperationNotAllowedException;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Activity;
import grp18.software.domain.Project;
import grp18.software.domain.Worker;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.*;
//William
class addWorkerToActivityWhiteBoxTest {
    private Activity activity1 = new Activity("Activity1","2022,01,01","2022,01,05");
    private Project project1 = new Project("Project1");
    private Worker worker1 = new Worker("WO");
    private Worker worker2 = new Worker("WT");
    private Worker worker3 = null;
    private Worker worker4 = new Worker("WF");
    private ErrorMessageHolder errorMessage;
    private RegistrationApp RApp;

    addWorkerToActivityWhiteBoxTest() throws IllegalDateException {
    }

    @BeforeEach
    private void SetUp(){ // Setup the project, activity and add workers to project
        addWorkers();
        RApp = new RegistrationApp();
        errorMessage = new ErrorMessageHolder();
        RApp.addProject(project1);
        try {
            project1.addActivity(activity1);
        } catch(OperationNotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    private void addWorkers(){ // Adds the workers, except worker3
        try {
            project1.addWorker(worker1);
            project1.addWorker(worker2);
            project1.addWorker(worker4);
        }catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Test
    @DisplayName("Case A")
    public void addWorkertoActivityWhiteBoxTestTestCaseA(){
        // Check that the same worker cant be added twice
        try {
            project1.addWorkerToActivity(worker1, activity1);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        try {
            project1.addWorkerToActivity(worker1, activity1);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        Assertions.assertEquals("Worker Already exists in database", errorMessage.getErrorMessage());
    }

    @Test
    @DisplayName("Case B")
    public void addWorkertoActivityWhiteBoxTestTestCaseB(){
        // Check if two unique workers can be added
        try {
            project1.addWorkerToActivity(worker1, activity1);
            project1.addWorkerToActivity(worker2,activity1);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }

        Assertions.assertEquals(2,activity1.getWorkers().size());
        Assertions.assertEquals("",errorMessage.getErrorMessage());
    }

    @Test
    @DisplayName("Case C")
    public void addWorkertoActivityWhiteBoxTestTestCaseC(){
        // Check if a non existant worker in the project can be added
        try {
            project1.addWorkerToActivity(worker3, activity1);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }

        Assertions.assertEquals("The worker is invalid",errorMessage.getErrorMessage());
        Assertions.assertEquals(0,activity1.getWorkers().size());
    }

    @Test
    @DisplayName("Case D")
    public void addWorkertoActivityWhiteBoxTestTestCaseD(){
        // Only add a single worker
        try {
            project1.addWorkerToActivity(worker4, activity1);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        Assertions.assertEquals(1,activity1.getWorkers().size());
        Assertions.assertEquals("",errorMessage.getErrorMessage());
    }


}