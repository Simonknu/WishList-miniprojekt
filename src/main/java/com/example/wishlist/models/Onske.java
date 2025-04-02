package com.example.wishlist.models;

public class Onske {
    private String name;
    private String description;
    private String link;
    private int ID;

    public Onske(){}

    public Onske(int ID, String name, String description, String link)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }
}