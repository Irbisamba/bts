package bts.model;

import java.io.Serializable;

public class Project implements Serializable {

    //private static int counter = 1;

    private long id;
    private String name;
    private String description;

    public Project(String name, String description) {
        id = System.currentTimeMillis();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
        //кавычки
    }
}
