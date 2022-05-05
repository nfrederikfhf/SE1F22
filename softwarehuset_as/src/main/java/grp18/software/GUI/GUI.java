/*
    AUTHOR = William
 */
package grp18.software.GUI;
import grp18.software.app.*;
import grp18.software.app.IllegalDateException;
import grp18.software.domain.*;
import grp18.software.tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class GUI {


    public static void main(String[] args) throws IllegalDateException {
        String[] workerNames = {"Naira","Pete","Gianfranco","Esther","John","Tajana","Yuudai","Gert","Leonas","Rama",
                "Elpídio","Kjeld","Enda","Tomislav","Halkyone","Haytham","Kliment","Mukesh","Enyinnaya","Emanoil"};
        // Possible change to initials if cumbersome
        // Define variables used in switch statement, as many are common across cases
        Scanner scanner = new Scanner(System.in); // Read user input from terminal
        int selection;
        Boolean run = true;
        String projectName;
        Project project;
        Activity activity;
        Worker worker;
        Event event;

        int projectID;
        String activityName;
        String workerName;

        String startDate = "0,0,0";
        String endDate = "0,0,0";
        String date = "0,0,0";
        String startTime = "0,0";
        String endTime = "0,0";
        StringToCalender startDatedata = new StringToCalender("0,0,0","0,0", "0,0");
        StringToCalender endDatedata = new StringToCalender("0,0,0","0,0", "0,0");

        // Generate workers
        for(int i = 0; i < 20; i++){
            RegistrationApp.INSTANCE.addWorker(new Worker(workerNames[i]));
        }

        System.out.println("Welcome to the project planner for Softwarehuset A/S");
        while(run){ // Runs until exit is selected by user
            System.out.print("Following operations are available: \n 1: Create project \n 2: Add activity \n" +
                    " 3: Add worker \n 4: Register time \n 5: Assign project manager \n" +
                    " 6: Rename project \n 7: Rename activity \n 8: Edit registered time \n" +
                    " 9: Get status\n 0: Exit \n");

            try {
                selection = Integer.parseInt(scanner.next() + scanner.nextLine()); // Read selection from terminal input

                switch (selection) {
                    case 0:
                        run = false; // Exit the while loop and the program likewise
                        break;

                    case 1: // Create project
                        System.out.println("Input project name: ");
                        projectName = scanner.nextLine();

                        System.out.println("Input project start date, as 'year,month,day': ");
                        startDate = scanner.nextLine();

                        System.out.println("Input project end date, as 'year,month,day': ");
                        endDate = scanner.nextLine();
                        try {
                            project = new Project(projectName, startDate, endDate);
                            RegistrationApp.INSTANCE.addProject(project); // Add the project, to this instance of Registration app
                            System.out.println("Project created with ID: " + project.getID());
                        } catch (IllegalDateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2: // Add an activity to a chosen project
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of the project you want to add an activity to?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }

                        System.out.println("You have selected project: " + projectID + "\nInput activity name: ");
                        activityName = scanner.nextLine();

                        System.out.println("Input activity start date, as 'year,month,day': ");
                        startDate = scanner.nextLine();

                        System.out.println("Input activity end date, as 'year,month,day': ");
                        endDate = scanner.nextLine();

                        // Create and add the activity
                        //activity = new Activity(activityName, startDatedata.dateCal,endDatedata.dateCal);

                        try {
                            activity = new Activity(activityName, startDate, endDate);
                            addActivity(projectID, activity); // Add an activity
                            System.out.println("Project created with ID: " + activity.getActivityName());
                        } catch (IllegalDateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3: // Add one of the pre-generated workers to an activity in of the created projects
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of the project you want to add worker to?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }
                        project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
                        getAvailableActivities(scanner, projectID); //Generate a list of available activities if wanted

                        System.out.println("You have selected project: " + projectID + "\nWhich activity?: ");
                        activityName = scanner.nextLine();
                        if (project.getActivityFromName(activityName) == null) { // Check if activity exists
                            System.out.println("Activity not found, try again");
                            break;
                        }
                        activity = project.getActivityFromName(activityName); // Get the activity object

                        getAvailableWorkers(scanner); //Generate a list of available workers if wanted
                        System.out.println("Which worker do you want to add: ");
                        workerName = scanner.nextLine();
                        if (RegistrationApp.INSTANCE.getWorkerFromInitials(workerName) == null) { // Check if worker exists
                            System.out.println("Worker not found, try again");
                            break;
                        }
                        worker = RegistrationApp.INSTANCE.getWorkerFromInitials(workerName); // Get the worker object

                        try { // Check if worker is already added to activity
                            project.addWorkerToActivity(worker, activity);
                            System.out.println("Worker " + workerName + " has been added to activity " + activityName);
                        } catch (OperationNotAllowedException e) {
                            System.out.println("Worker is already found in activity!"); // Throw error
                        }
                        break;

                    case 4:
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of the project you want to register hours to?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }
                        project = RegistrationApp.INSTANCE.getProjectFromID(projectID);

                        getAvailableActivities(scanner, projectID); //Generate a list of available activities if wanted
                        System.out.println("You have selected project: " + projectID + "\nWhich activity are you working on?");
                        activityName = scanner.nextLine();
                        if (project.getActivityFromName(activityName) == null) { // Check if activity exists
                            System.out.println("Activity not found, try again");
                            break;
                        }
                        activity = project.getActivityFromName(activityName); // Get the activity object

                        getAvailableWorkers(scanner); //Generate a list of available workers if wanted
                        System.out.println("What are your initials?"); // Name from generated worker list
                        workerName = scanner.nextLine();
                        if (RegistrationApp.INSTANCE.getWorkerFromInitials(workerName) == null) { // Check if worker exists
                            System.out.println("Worker not found, try again");
                            break;
                        }
                        worker = RegistrationApp.INSTANCE.getWorkerFromInitials(workerName); // Get the worker object

                        System.out.println("Input date: ");
                        date = scanner.nextLine();
                        System.out.println("Input start time as 'hour,minutes': ");
                        startTime = scanner.nextLine();
                        System.out.println("Input end time as 'hour,minutes': ");
                        endTime = scanner.nextLine();

                        try { // Check for overlap between time registered
                            worker.registerHours(startTime, endTime, date, activity);
                        } catch (EventOverlapException e) {
                            System.out.println("Time already registered in this timeframe");
                        }

                        System.out.println("Hours added to activity: " + activityName + " in project: " + projectID);
                        break;

                    case 5:
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of the project you want to register hours to?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }
                        project = RegistrationApp.INSTANCE.getProjectFromID(projectID);

                        getAvailableWorkers(scanner); //Generate a list of available workers if wanted
                        System.out.println("You have selected project: " + projectID + "\nWhich worker do you wish to assign" +
                                " as project manager?: ");
                        workerName = scanner.nextLine();
                        if (RegistrationApp.INSTANCE.getWorkerFromInitials(workerName) == null) { // Check if worker exists
                            System.out.println("Worker not found, try again");
                            break;
                        }
                        worker = RegistrationApp.INSTANCE.getWorkerFromInitials(workerName); // Get the worker object
                        try {
                            project.assignManager(worker); // Assign as project manager in registration app
                            worker.setProjectManager(true); // Mark the unique worker object as project manager

                            System.out.println("You have assigned " + workerName + " as project manager of project " + projectID);
                        } catch (OperationNotAllowedException e){
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6:
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of the project you want to rename?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }
                        project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
                        System.out.println("Current project name is: " + project.getName() + "\nInput new name: ");
                        projectName = scanner.nextLine();
                        project.setName(projectName); // Update the project with the new name

                        System.out.println("Project: " + projectID + " has been renamed to: " + projectName);
                        break;

                    case 7:
                        getAvailableProjects(scanner); // Generate a list of available projects if wanted
                        System.out.println("What is the ID of project containing the activity?");
                        try {
                            projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                        } catch (Exception e) { // Catch any mistypes to avoid program exits.
                            System.out.println("Input is not an integer, try again");
                            break;
                        }
                        if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                            System.out.println("Project not found, try again");
                            break;
                        }
                        project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
                        getAvailableActivities(scanner, projectID); //Generate a list of available activities if wanted
                        System.out.println("Which activity do you want to rename?");
                        activityName = scanner.nextLine();

                        if (project.getActivityFromName(activityName) == null) { // Check if activity exists
                            System.out.println("Activity not found, try again");
                            break;
                        }

                        activity = project.getActivityFromName(activityName); // Get the activity object
                        System.out.println("What is the new name of the activity?");
                        String newActivityName = scanner.nextLine();
                        activity.setActivityName(newActivityName); // Update activity with new name

                        System.out.println("Activity: " + activityName + " has been renamed to: " + newActivityName);
                        break;

                    case 9:
                        System.out.println("1. Entire System\n2. A specific problem");
                        StringBuilder report = new StringBuilder();
                        String choice = scanner.nextLine();
                        if (choice.trim().equals("1")){
                            RegistrationApp.INSTANCE.getStatusReport(report);
                        } else if (choice.trim().equals("2")){
                            getAvailableProjects(scanner);
                            try {
                                projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                            } catch (Exception e) { // Catch any mistypes to avoid program exits.
                                System.out.println("Input is not an integer, try again");
                                break;
                            }

                            if (RegistrationApp.INSTANCE.getProjectFromID(projectID) == null) { // Check if project exists
                                System.out.println("Project not found, try again");
                                break;
                            }
                            project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
                            project.getStatusReport("",report);
                        }
                        System.out.println(report);
                        break;
                }
            } catch (NumberFormatException e){
                System.out.println("Not possible, try again");
            }

        }
    }

    public static void addActivity(int projectID, Activity activity){
        try { // Check for activity overlap, or if activity already exists
            RegistrationApp.INSTANCE.getProjectFromID(projectID).addActivity(activity);
            System.out.println("Activity: " + activity.getActivityName() + " added");
        } catch (OperationNotAllowedException e) {
            System.out.println("Activity already exists! try again");
        }
    }

    public static void getAvailableProjects(Scanner scanner){
        // Function to output all available projects, defined in the instance of Registration App
        String available;
        List<Project> projects = new ArrayList<>();
        System.out.println("Do you want a list of available projects? [y/n]");
        available = scanner.nextLine();
        if(available.equals("y")){
            projects = RegistrationApp.INSTANCE.getProjects();
            for(Project project1 : projects){
                System.out.println(project1.getName() + " ID: " + project1.getID());
            }
        }
    }
    public static void getAvailableActivities(Scanner scanner, int projectID){
        // Function to output all available activities, defined in the instance of Registration App
        String available;
        List<Activity> activities = new ArrayList<>();
        System.out.println("Do you want a list of available activities? [y/n]");
        available = scanner.nextLine();
        if(available.equals("y")){
            activities = RegistrationApp.INSTANCE.getProjectFromID(projectID).getActivities();
            for(Activity activity1 : activities){
                System.out.println("Name: " + activity1.getActivityName());
            }
        }
    }

    public static void getAvailableWorkers(Scanner scanner){
        // Function to output all available workers, defined in the instance of Registration App
        String available;
        List<Worker> workers = new ArrayList<>();
        System.out.println("Do you want a list of available workers? [y/n]");
        available = scanner.nextLine();
        if(available.equals("y")){
            workers = RegistrationApp.INSTANCE.getWorkers();
            for(Worker worker1 : workers){
                System.out.println("Initials: " + worker1.getInitials());
            }
        }
    }
}
