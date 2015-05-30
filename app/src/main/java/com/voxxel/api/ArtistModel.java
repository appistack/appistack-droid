package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class ArtistModel extends BaseResponse {
    private Integer id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String description;
    private String headshot;

    //TODO: sounds as SoundModel[] ??

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() { return firstName + " " + lastName; }

    public String getDescription() {
        return description;
    }

    public String getHeadshot() {
        return headshot;
    }
}
