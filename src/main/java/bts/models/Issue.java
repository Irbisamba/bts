package bts.models;

public class Issue {
    private static int counter = 1;
    private int id;
    private String title;
    private String description;
    private IssuePriority priority;
    private IssueStatus status;
    private Project project;
    private User user;

}
