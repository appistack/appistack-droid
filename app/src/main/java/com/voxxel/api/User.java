package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class User extends BaseResponse {

    private String id;
    private String username;
    private String email;
    private String uid;

    private String name;
    private String description;
    private String image;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
