package bts.service;

import bts.model.Issue;
import bts.model.Project;
import bts.model.User;

import java.util.List;

public interface BTSService {

    List<User> getAllUsers();

    List<Project> getAllProjects();

    List<Issue> getAllIssues();

    void addUser(User user);

    void addProject(Project project);

    void addIssue(Issue issue);

    List<Issue> findIssuesByProjectAndUser(String projectName, String userName);



}
