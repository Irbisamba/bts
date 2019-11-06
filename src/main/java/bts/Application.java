package bts;

import bts.models.Issue;
import bts.models.IssuePriority;
import bts.models.Project;
import bts.models.User;
import bts.service.BTSService;
import bts.service.BTSServiceImpl;
import bts.utils.ConsoleHelper;

import java.util.List;
import java.util.Scanner;

public class Application {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final BTSService btsService = new BTSServiceImpl();

    public static void main(String[] args) {
        Application.application();

    }

    private static void application(){
        int command = 0;
        while(command != 8){
            ConsoleHelper.showMenu();

            command = SCANNER.nextInt();

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
                    System.out.println(btsService.getAllProjects());
                    break;
                case 5:
                    System.out.println("Loading all Users");
                    System.out.println(btsService.getAllUsers());
                    break;
                case 6:
                    System.out.println("Loading all Issues");
                    System.out.println(btsService.getAllIssues());
                    break;
                case 7:
                    findIssues();
                    break;
                case 8:
                    System.out.println("Buy!");
                    break;
                default:
                    System.out.println("Sorry, please enter valid command");

            }
        }
    }
    private static void addProject(){
        System.out.println("Adding new Project");
        System.out.println("Please, enter Project name");
        String name = SCANNER.next();

        System.out.println("Please, enter description");
        String descr = SCANNER.next();

        btsService.addProject(new Project(name, descr));
        System.out.println("Project added");
        //System.out.println("Press Enter to proceed");

    }
    private static void addUser(){
        System.out.println("Adding new User");
        System.out.println("Please, enter User name");

        String name = SCANNER.next();

        btsService.addUser(new User(name));

        System.out.println("User added");
        //System.out.println("Press Enter to proceed");
    }
    private static void addIssue(){
        System.out.println("Adding new Issue");
        System.out.println("Please, enter Issue title");

        String title = SCANNER.next();

        System.out.println("Please, enter Issue description");

        String descr = SCANNER.next();

        System.out.println("Please, enter Issue priority");
        ConsoleHelper.showPriorities();

        IssuePriority prio = IssuePriority.valueOf(SCANNER.next());

        System.out.println("Please, enter Project name");

        String projectName = SCANNER.next();

        System.out.println("Please, enter Author name");

        String authorName = SCANNER.next();

        btsService.addIssue(new Issue(title, descr, prio, projectName, authorName));

        System.out.println("Issue added");
        //System.out.println("Press Enter to proceed");
    }
    private static List<Issue> findIssues(){
        return null;
    }



}
