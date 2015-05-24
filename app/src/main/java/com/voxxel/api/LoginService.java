package com.voxxel.api;

import retrofit.http.*;

public interface LoginService {
    //user signup
    @POST("/auth")
    User auth();

    @POST("/auth/sign_in")
    AccessToken accessToken();

    @GET("/auth/validate_token")
    AccessToken validateAccessToken();

    //TODO: implement new class for signout response?
//    @DELETE("/auth/sign_out")
//    AccessToken signOutResponse();
}
