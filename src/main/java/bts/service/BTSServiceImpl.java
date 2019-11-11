package bts.service;

import bts.model.Issue;
import bts.model.Project;
import bts.model.User;

import java.util.ArrayList;
import java.util.List;

public class BTSServiceImpl implements BTSService {

    private static final String PROJECTS_FILE_NAME = "projects.txt";
    private static final String USERS_FILE_NAME = "users.txt";
    private static final String ISSUES_FILE_NAME = "issues.txt";

    private List<Project> projects;
    private List<User> users;
    private List<Issue> issues;

    private static final Loader loader = new LoaderImpl();
    private static final Writer writer = new WriterImpl();

    public BTSServiceImpl() {
        this.projects = loader.load(PROJECTS_FILE_NAME);
        this.users = loader.load(USERS_FILE_NAME);
        this.issues = loader.load(ISSUES_FILE_NAME);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Project> getAllProjects() {
        return projects;
    }

    @Override
    public List<Issue> getAllIssues() {
        return issues;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        writer.write(users, USERS_FILE_NAME);
    }

    @Override
    public void addProject(Project project) {
        projects.add(project);
        writer.write(projects, PROJECTS_FILE_NAME);
    }

    @Override
    public void addIssue(Issue issue) {
        issues.add(issue);
        writer.write(issues, ISSUES_FILE_NAME);
    }

    @Override
    public List<Issue> findIssuesByProjectAndUser(String projectName, String userName) {
        List<Issue> result = new ArrayList<>();
        for(int i = 0; i<issues.size(); i++){
            if(projectName.equalsIgnoreCase(issues.get(i).getProject().getName())){
                if(userName.equalsIgnoreCase(issues.get(i).getAuthor().getName())){
                    result.add(issues.get(i));
                }
            }
        }

        return result;
    }
}
