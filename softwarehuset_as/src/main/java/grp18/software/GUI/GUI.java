/*
    AUTHOR = William
 */
package grp18.software.GUI;
import grp18.software.app.*;
import grp18.software.domain.*;
import grp18.software.dto.*;
import grp18.software.tools.*;
import java.util.Scanner;



public class GUI {

    private ErrorMessageHolder errorMessage;

    public GUI( ErrorMessageHolder errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static void main(String[] args){
        String[] workerNames = {"Naira","Pete","Gianfranco","Esther","John","Tajana","Yuudai","Gert","Leonas","Rama",
                "Elpídio","Kjeld","Enda","Tomislav","Halkyone","Haytham","Kliment","Mukesh","Enyinnaya","Emanoil"};
        // Possible change to initials if cumbersome
        Scanner scanner = new Scanner(System.in);
        int selection;
        Boolean run = true;
        String projectName;
        String startDate;
        String endDate;
        Project project;
        Activity activity;
        Worker worker;
        Event event;

        int projectID;
        String activityName;
        String workerName;

        String date = "0,0,0";
        String startTime = "0,0";
        String endTime = "0,0";

        // Generate workers
        for(int i = 0; i < 20; i++){
            RegistrationApp.INSTANCE.addWorker(new Worker(workerNames[i]));
        }

        System.out.println("Welcome to the project planner for Softwarehuset A/S");
        while(run){
            System.out.print("Following operations are available: \n 1: Create project \n 2: Add activity \n 3: Add worker \n 4: Register time \n 5: Rename project \n 6: Rename activity \n 7: Edit registered time \n 8: Exit \n");

            selection = Integer.parseInt(scanner.next() + scanner.nextLine()); // Read selection from terminal input

            switch(selection){
                case 1: // Create project
                    System.out.println("Input project name: ");
                    projectName = scanner.nextLine();

                    System.out.println("Input project start date, as 'year,month,day': ");
                    startDate = scanner.nextLine();
                    StringToCalender startDatedata = new StringToCalender(startDate,"0,0", "0,0");

                    System.out.println("Input project end date, as 'year,month,day': ");
                    endDate = scanner.nextLine();
                    StringToCalender endDatedata = new StringToCalender(endDate,"0,0", "0,0");

                    project = new Project(projectName,startDatedata.dateCal,endDatedata.dateCal);
                    RegistrationApp.INSTANCE.addProject(project);

                    System.out.println("Project created with ID: " + project.getID());
                    break;

                case 2: // Add an activity to a chosen project
                    System.out.println("Which project do you want to add an activity to?");
                    try {
                        projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                    }catch (Exception e){ // Catch any mistypes to avoid program exits.
                        System.out.println("Input is not an integer, try again");
                        break;
                    }
                    if(RegistrationApp.INSTANCE.getProjectFromID(projectID) == null){ // Check if project exists
                        System.out.println("Project not found, try again");
                        break;
                    }

                    System.out.println("You have selected project: " + projectID + "\nInput activity name: ");
                    activityName = scanner.nextLine();

                    System.out.println("Input activity start date, as 'year,month,day': ");
                    startDate = scanner.nextLine();
                    StringToCalender startDateActivity = new StringToCalender(startDate,"0,0", "0,0");

                    System.out.println("Input activity end date, as 'year,month,day': ");
                    endDate = scanner.nextLine();
                    StringToCalender endDateActivity = new StringToCalender(endDate,"0,0", "0,0");

                    // Create and add the activity
                    activity = new Activity(activityName, startDateActivity.dateCal,endDateActivity.dateCal);
                    addActivity(projectID, activity); // Add an activity
                    break;

                case 3: // Add one of the pre-generated workers to an activity in of the created projects
                    System.out.println("Which project do you want to add worker to?");
                    try {
                        projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                    }catch (Exception e){ // Catch any mistypes to avoid program exits.
                        System.out.println("Input is not an integer, try again");
                        break;
                    }
                    if(RegistrationApp.INSTANCE.getProjectFromID(projectID) == null){ // Check if project exists
                        System.out.println("Project not found, try again");
                        break;
                    }
                    project = RegistrationApp.INSTANCE.getProjectFromID(projectID);

                    System.out.println("You have selected project: " + projectID + "\nWhich activity?: ");
                    activityName = scanner.nextLine();
                    activity = project.getActivityFromName(activityName); // Get the activity object

                    System.out.println("Which worker do you want to add: ");
                    workerName = scanner.nextLine();
                    worker = RegistrationApp.INSTANCE.getWorkerFromInitials(workerName); // Get the worker object

                    try { // Check if worker is already added to activity
                        project.addWorkerToActivity(worker,activity);
                        System.out.println("Worker " + workerName + " has been added to activity " + activityName);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e); // Throw error
                    }
                    break;
                case 4:
                    System.out.println("Which project do you want to register hours to?");
                    try {
                        projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                    }catch (Exception e){ // Catch any mistypes to avoid program exits.
                        System.out.println("Input is not an integer, try again");
                        break;
                    }
                    if(RegistrationApp.INSTANCE.getProjectFromID(projectID) == null){ // Check if project exists
                        System.out.println("Project not found, try again");
                        break;
                    }
                    project = RegistrationApp.INSTANCE.getProjectFromID(projectID);

                    System.out.println("You have selected project: " + projectID + "\nWhich activity are you working on?");
                    activityName = scanner.nextLine();
                    activity = project.getActivityFromName(activityName); // Get the activity object

                    System.out.println("What are your initials?"); // Name from generated worker list
                    workerName = scanner.nextLine();
                    worker = RegistrationApp.INSTANCE.getWorkerFromInitials(workerName); // Get the worker object

                    System.out.println("Input date: ");
                    date = scanner.nextLine();
                    System.out.println("Input start time as 'hour,minutes': ");
                    startTime = scanner.nextLine();
                    System.out.println("Input end time as 'hour,minutes': ");
                    endTime = scanner.nextLine();

                    StringToCalender dateData = new StringToCalender(date, startTime, endTime);

                    //event = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity, 1);

                    try {
                        worker.registerHours(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity);
                    } catch (EventOverlapException e) {
                        System.out.println(e);
                    }

                    System.out.println("Hours added to activity: " + activityName + " in project: " + projectID);
                    break;

                case 5:
                    System.out.println("Which project do you want to rename?");
                    try {
                        projectID = Integer.parseInt(scanner.next() + scanner.nextLine());
                    }catch (Exception e){ // Catch any mistypes to avoid program exits.
                        System.out.println("Input is not an integer, try again");
                        break;
                    }
                    if(RegistrationApp.INSTANCE.getProjectFromID(projectID) == null){ // Check if project exists
                        System.out.println("Project not found, try again");
                        break;
                    }
                    project = RegistrationApp.INSTANCE.getProjectFromID(projectID);
                    System.out.println("Current project name is: " + project.getName() + "\nInput new name: ");
                    projectName = scanner.nextLine();
                    project.setName(projectName);

                    System.out.println("Project: " + projectID + " has been renamed to: " + projectName);
                    break;
                case 8:
                    run = false; // Exit the while loop and the program likewise
                    break;
            }

        }
    }

    public static void addActivity(int projectID, Activity activity){
        try { // Check for activity overlap, or if activity already exists
            RegistrationApp.INSTANCE.getProjectFromID(projectID).addActivity(activity);
            System.out.println("Activity added");
        } catch (OperationNotAllowedException e) {
            System.out.println(e);
            //errorMessage.setErrorMessage(e.getMessage());
        }
    }
}
