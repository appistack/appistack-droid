package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class SoundModel extends BaseResponse {
    private Integer id;

    @SerializedName("artist_id")
    private Integer artistId;

    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
