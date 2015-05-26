package com.voxxel.api;

import retrofit.http.*;

public interface AuthService {
    @POST("/auth")
    @FormUrlEncoded
    UserModel signup(@Field("email") String email,
                     @Field("username") String username,
                     @Field("password") String password,
                     @Field("password_confirmation") String passwordConfirm);

    @POST("/auth/sign_in")
    @FormUrlEncoded
    AccessTokenModel signin(@Field("email") String email,
                            @Field("password") String password);

    @GET("/auth/validate_token")
    AccessTokenModel validateAccessToken();

    //TODO: implement new class for signout response?
//    @DELETE("/auth/sign_out")
//    AccessTokenModel signOutResponse();
}
