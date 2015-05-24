package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface SoundService {
    @GET("/sounds")
    List<SoundModel> getSounds();

    @GET("/sounds/{id}")
    SoundModel getSound(@Path("id") int id);
}
