package bts;

import bts.models.Issue;
import bts.models.IssuePriority;
import bts.models.Project;
import bts.models.User;
import bts.service.BTSService;
import bts.service.BTSServiceImpl;
import bts.utils.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static final BTSService btsService = new BTSServiceImpl();

    public static void main(String[] args) throws Exception{
        Application.application();

    }

    private static void application() throws IOException{
        int command = 0;
        while(command != 8){
            command = 0;
            ConsoleHelper.showMenu();
            try {
                command = Integer.parseInt(READER.readLine());
            }catch(IOException|NumberFormatException e){
                System.out.println("Exception");
            }

            switch (command){
                case 1:
                    addProject();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    addIssue();
                    break;
                case 4:
                    System.out.println("Loading all Projects");
                    for(Project p : btsService.getAllProjects())
                        System.out.println(p);

                    System.out.println("Press Enter to proceed");
                    READER.readLine();
                    break;
                case 5:
                    System.out.println("Loading all Users");
                    for(User u : btsService.getAllUsers())
                        System.out.println(u);

                    System.out.println("Press Enter to proceed");
                    READER.readLine();
                    break;
                case 6:
                    System.out.println("Loading all Issues");
                    for(Issue i : btsService.getAllIssues())
                        System.out.println(i);

                    System.out.println("Press Enter to proceed");
                    READER.readLine();
                    break;
                case 7:
                    for(Issue i : findIssues())
                        System.out.println(i);

                    System.out.println("Press Enter to proceed");
                    READER.readLine();
                    break;
                case 8:
                    System.out.println("Buy!");
                    break;
                default:
                    System.out.println("Sorry, please enter valid command");
                    System.out.println("Press Enter to proceed");
                    READER.readLine();

            }
        }
    }
    private static void addProject() throws IOException {
        System.out.println("Adding new Project");
        System.out.println("Please, enter Project name:");
        String name = READER.readLine();

        System.out.println("Please, enter description:");
        String description = READER.readLine();


        btsService.addProject(new Project(name, description));
        System.out.println("Project added.");
        System.out.println("Press Enter to proceed");
        String s = READER.readLine();


    }
    private static void addUser() throws IOException{
        System.out.println("Adding new User");
        System.out.println("Please, enter User name:");

        String name = READER.readLine();

        btsService.addUser(new User(name));

        System.out.println("User added");
        System.out.println("Press Enter to proceed");
        String s = READER.readLine();
    }
    private static void addIssue() throws IOException{
        System.out.println("Adding new Issue");
        System.out.println("Please, enter Issue title:");

        String title = READER.readLine();

        System.out.println("Please, enter Issue description:");

        String descr = READER.readLine();

        IssuePriority prio = readPriority();

        System.out.println("Please, enter Project name:");

        String projectName = READER.readLine();

        System.out.println("Please, enter Author name");

        String authorName = READER.readLine();

        btsService.addIssue(new Issue(title, descr, prio, projectName, authorName));

        System.out.println("Issue added.");
        System.out.println("Press Enter to proceed");
        String s = READER.readLine();
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



}
