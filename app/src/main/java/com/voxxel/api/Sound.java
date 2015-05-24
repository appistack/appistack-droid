package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class Sound extends BaseResponse {
    private Integer id;

    @SerializedName("artist_id")
    private Integer artistId;

    private String name;
    private String description;
}
