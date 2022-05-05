package grp18.software.app;

import grp18.software.domain.Project;
import grp18.software.domain.Worker;

import java.util.*;
//William
public class RegistrationApp extends Observable{

    private List<Worker> workers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    public static final RegistrationApp INSTANCE = new RegistrationApp();

    //Jacob
    public Project getProjectFromID(int projectID) {
        return projects.stream().filter(x -> x.getID()==(projectID)).findFirst().orElse(null);
    }
    //Niels
    public void addProject(Project project){
        project.setID(projects.size()+22001);
        this.projects.add(project);
    }

    public List<Project> getProjects(){
        return this.projects;
    }

    //William
    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public List<Worker> getWorkers() {return this.workers;}

    //Jacob
    public Worker getWorkerFromInitials(String initials) {
        return workers.stream().filter(x -> x.getInitials().equals(initials)).findFirst().orElse(null);
    }

    //Jacob
    public void getStatusReport(StringBuilder stringBuilder){
        stringBuilder.append("Status report:\n");
        for (Project project : projects){
            String prefix;

            Boolean isLast = project == projects.get(projects.size() - 1);

            if (isLast){
                prefix = "   ";
            }else{
                prefix = "|  ";
            }

            project.getStatusReport(prefix, stringBuilder);
        }
    }
}
