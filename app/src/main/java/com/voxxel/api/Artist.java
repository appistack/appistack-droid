package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class Artist extends BaseResponse {
    private Integer id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String description;
    private String headshot;

    //TODO: sounds as Sound[] ??

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getHeadshot() {
        return headshot;
    }
}
