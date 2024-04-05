package org.example.entity;


public class Status {
    private int id;
    private String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
