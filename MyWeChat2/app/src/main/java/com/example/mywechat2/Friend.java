package com.example.mywechat2;

public class Friend {

    private int id;
    private int imageid;
    private String name;

    public Friend(int id, int imageid, String name) {
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
