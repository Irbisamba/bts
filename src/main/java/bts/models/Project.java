package bts.models;

import java.io.Serializable;

public class Project implements Serializable {

    private static int counter = 1;

    private int id;
    private String name;
    private String description;

    public Project(String name, String description) {
        id = counter++;
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

    public int getId() {
        return id;
    }
}
