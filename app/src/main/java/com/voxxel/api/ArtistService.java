package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface ArtistService {

    @GET("/artists")
    List<ArtistModel> getArtists();

    @GET("/artists/{id}")
    ArtistModel getArtist(@Path("id") int id);
}
