package bts;

import bts.model.Issue;
import bts.model.IssuePriority;
import bts.model.Project;
import bts.model.User;
import bts.service.BTSService;
import bts.service.BTSServiceImpl;
import bts.util.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static final BTSService btsService = new BTSServiceImpl();

    public static void main(String[] args) throws Exception{
        application();

    }

    private static void application() throws IOException{
        int command = 0;
        while(command != 8){
            command = 0;
            ConsoleHelper.showMenu();
            try {
                command = Integer.parseInt(READER.readLine());
            }catch(IOException|NumberFormatException e){
                System.out.println("Something wrong");
            }

            switch (command){
                case 1:
                    addProject();
                    goOn();
                    break;
                case 2:
                    addUser();
                    goOn();
                    break;
                case 3:
                    addIssue();
                    goOn();
                    break;
                case 4:
                    System.out.println("Loading all Projects");
                    for(Project p : btsService.getAllProjects())
                        System.out.println(p);

                    goOn();
                    break;
                case 5:
                    System.out.println("Loading all Users");
                    for(User u : btsService.getAllUsers())
                        System.out.println(u);

                    goOn();
                    break;
                case 6:
                    System.out.println("Loading all Issues");
                    for(Issue i : btsService.getAllIssues())
                        System.out.println(i);

                    goOn();
                    break;
                case 7:
                    for(Issue i : findIssues())
                        System.out.println(i);

                    goOn();
                    break;
                case 8:
                    System.out.println("Buy!");
                    READER.close();
                    break;
                default:
                    System.out.println("Sorry, enter valid command");
                    goOn();

            }
        }
    }
    private static void addProject() throws IOException {
        System.out.println("Adding new Project");
        System.out.println("Please, enter Project name:");
        String name = READER.readLine();

        System.out.println("Please, enter description:");
        String description = READER.readLine();

        if(name.isEmpty()||description.isEmpty()){
            System.out.println("Failed");
            System.out.println("Name and description can not be empty");
        } else{
            for(Project project : btsService.getAllProjects()){
                if(project.getName().equals(name)){
                    System.out.println("Project already exists");
                    return;
                }
            }
        btsService.addProject(new Project(name, description));
        System.out.println("Project added."); }


    }
    private static void addUser() throws IOException{
        System.out.println("Adding new User");
        System.out.println("Please, enter User name:");

        String name = READER.readLine();

        if(name.isEmpty()){
            System.out.println("Failed");
            System.out.println("Name can not be empty");
        } else{
            for(User user : btsService.getAllUsers()){
                if(user.getName().equals(name)){
                    System.out.println("User already exists");
                    return;
                }
            }
        btsService.addUser(new User(name));
        System.out.println("User added");
        }
    }
    private static void addIssue() throws IOException{
        System.out.println("Adding new Issue");
        System.out.println("Please, enter Issue title:");

        String title = READER.readLine();

        System.out.println("Please, enter Issue description:");

        String descr = READER.readLine();
        if(title.isEmpty()||descr.isEmpty()){
            System.out.println("Failed");
            System.out.println("Title and description can not be empty");
        } else{

        IssuePriority prio = readPriority();

        System.out.println("Please, enter Project name:");

        String projectName = READER.readLine();
        Project issueProject = null;
        for(Project project : btsService.getAllProjects()){
            if(projectName.equals(project.getName()))
                 issueProject = project;
            continue;
        } if(issueProject==null){
                System.out.println("Creating new project");
                System.out.println("Enter project description");
                issueProject = new Project(projectName, READER.readLine());
                btsService.addProject(issueProject);
        }

        System.out.println("Please, enter Author name");

        String authorName = READER.readLine();
        User author = null;
        for(User user : btsService.getAllUsers()){
            if(authorName.equals(user.getName()))
                author = user;
            continue;
        } if(author==null){
            author = new User(authorName);
            btsService.addUser(author);
            }

        btsService.addIssue(new Issue(title, descr, prio, issueProject, author));

        System.out.println("Issue added."); }
    }
    private static List<Issue> findIssues() throws IOException{
        System.out.println("Finding Issues");
        System.out.println("Please, enter Project name:");

        String projectName = READER.readLine();

        System.out.println("Please, enter Author name:");

        String authorName = READER.readLine();
        return btsService.findIssuesByProjectAndUser(projectName, authorName);
    }

    private static IssuePriority readPriority() throws IOException{
        System.out.println("Please, enter Issue priority:");
        ConsoleHelper.showPriorities();
        IssuePriority prio = null;
        try {
            prio = IssuePriority.valueOf(READER.readLine());
        }catch (IllegalArgumentException | NullPointerException e){
            System.out.println("Sorry, please enter valid command");
            readPriority();
        }
        return prio;
    }

    public static void goOn() throws IOException{
        System.out.println("Press Enter to proceed");
        String s = READER.readLine();
    }


}
