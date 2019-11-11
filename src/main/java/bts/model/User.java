package bts.model;

import java.io.Serializable;

public class User implements Serializable {

    //private static int counter = 1;

    private long id;
    private String name;

    public User(String name) {
        id = System.currentTimeMillis();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
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
