package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface ArtistService {

    @GET("/api/v1/artists")
    @Headers("Content-Type:application/json")
    List<ArtistModel> getArtists();

    @GET("/api/v1/artists/{id}")
    @Headers("Content-Type:application/json")
    ArtistModel getArtist(@Path("id") int id);
}
