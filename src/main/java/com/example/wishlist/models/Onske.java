package com.example.wishlist.models;

public class Onske {
    private String name;
    private String description;
    private String link;



    public Onske(String name, String description, String link){
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}


