package com.example.wishlist.models;

public class Bruger {
    private String userName;
    private String password;
    //private String firstName;

    public Bruger(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public Bruger(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
