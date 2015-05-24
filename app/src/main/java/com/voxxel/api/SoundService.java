package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface SoundService {
    @GET("/sounds")
    List<Sound> getSounds();

    @GET("/sounds/{id}")
    Sound getSound(@Path("id") int id);
}
