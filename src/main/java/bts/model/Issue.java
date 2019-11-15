package bts.model;

import java.io.Serializable;

public class Issue implements Serializable {

    //private static int counter = 1;

    private int id;
    private String title;
    private String description;
    private IssuePriority priority;
    private Project project;
    private User author;

    public Issue(String title, String description, IssuePriority priority, Project project, User author) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.project = project;
        this.author = author;
    }

    public Issue(int id, String title, String description, IssuePriority priority, Project project, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.project = project;
        this.author = author;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", authorName='" + author.getName() + '\'' +
                ", projectName='" + project.getName() + '\'' +
                '}';
    }
}
