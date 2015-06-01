package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface SoundService {
    @GET("/api/v1/sounds")
    List<SoundModel> getSounds();

    @GET("/api/v1/sounds/{id}")
    SoundModel getSound(@Path("id") Long id);
}
