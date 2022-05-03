package grp18.software.app;

import grp18.software.domain.Project;
import grp18.software.domain.Worker;

import java.util.*;

public class RegistrationApp extends Observable{

    private List<Worker> workers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DateServer dateServer = new DateServer();
    public static final RegistrationApp INSTANCE = new RegistrationApp();


    public Project getProjectFromID(int projectID) {
        return projects.stream().filter(x -> x.getID()==(projectID)).findFirst().orElse(null);
    }

    public void addProject(Project project){
        project.setID(projects.size()+22001);
        this.projects.add(project);
    }

    public List<Project> getProjects(){
        return this.projects;
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public List<Worker> getWorkers() {return this.workers;}

    public Worker getWorkerFromInitials(String initials) {
        return workers.stream().filter(x -> x.getInitials().equals(initials)).findFirst().orElse(null);
    }

    public void printStatusReport(){
        System.out.println("Status report:");
        for (Project project : projects){
            String prefix = "";

            Boolean isLast = project == projects.get(projects.size() - 1);

            if (isLast){
                prefix = "   ";
            }else{
                prefix = "|  ";
            }

            project.printStatusReport(prefix);
        }
    }
}
