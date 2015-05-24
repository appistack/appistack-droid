package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface ArtistService {

    @GET("/artists")
    List<Artist> getArtists();

    @GET("/artists/{id}")
    Artist getArtist(@Path("id") int id);
}
