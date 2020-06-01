package com.kyriexu.homework06.pojo;

public class Friend {
    private int id;

    private String name;

    public Friend(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Friend() {
    }

    public Friend(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
