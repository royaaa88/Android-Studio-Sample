package com.example.example1.mywechat;

public class Player {

    private int id;
    private int imageid;
    private String name;

    public Player(int id, int imageid, String name) {
        this.id = id;
        this.imageid = imageid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getImageid() {
        return imageid;
    }

    public String getName() {
        return name;
    }
}
