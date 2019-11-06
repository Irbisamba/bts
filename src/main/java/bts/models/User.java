package bts.models;

import java.io.Serializable;

public class User implements Serializable {

    private static int counter = 1;

    private int id;
    private String name;

    public User(String name) {
        id = counter++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
        //кавычки
    }
}
