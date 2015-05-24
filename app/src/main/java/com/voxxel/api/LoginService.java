package com.voxxel.api;

import retrofit.http.*;

public interface LoginService {
    //user signup
    @POST("/auth")
    UserModel auth();

    @POST("/auth/sign_in")
    AccessTokenModel accessToken();

    @GET("/auth/validate_token")
    AccessTokenModel validateAccessToken();

    //TODO: implement new class for signout response?
//    @DELETE("/auth/sign_out")
//    AccessTokenModel signOutResponse();
}
