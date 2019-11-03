package bts.service;

import bts.models.Issue;
import bts.models.Project;
import bts.models.User;

import java.util.List;

public class BTSServiseImpl implements BTSService {

    private static final String PROJECTS_FILE_NAME = "projects.txt";
    private static final String USERS_FILE_NAME = "users.txt";
    private static final String ISSUES_FILE_NAME = "issues.txt";

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public List<Issue> getAllIssues() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addProject(Project project) {

    }

    @Override
    public void addIssue(Issue issue) {

    }

    @Override
    public List<Issue> findIssuesByProjectAndUser(String projectName, String userName) {
        return null;
    }
}
