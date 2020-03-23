package com.example.integrationwithwebservices;

public class Post {

    private String name;
    private String message;
    private String profile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Post(String name, String message, String profile) {
        this.name = name;
        this.message = message;
        this.profile = profile;
    }
}
