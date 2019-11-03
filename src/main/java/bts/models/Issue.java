package bts.models;

import java.io.Serializable;

public class Issue implements Serializable {

    private static int counter = 1;

    private int id;
    private String title;
    private String description;
    private IssuePriority priority;
    private String projectName;
    private String authorName;

    public Issue(String title, String description, IssuePriority priority, String projectName, String authorName) {
        id = counter++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.projectName = projectName;
        this.authorName = authorName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public void setPriority(IssuePriority priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
}
