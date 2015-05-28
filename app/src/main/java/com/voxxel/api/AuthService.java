package com.voxxel.api;

import retrofit.Callback;
import retrofit.client.Response;
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
    void signin(@Field("email") String email,
                            @Field("password") String password,
                            Callback<Response> cb);

    @GET("/auth/validate_token")
    AccessTokenModel validateAccessToken();

    //TODO: implement new class for signout response?
//    @DELETE("/auth/sign_out")
//    AccessTokenModel signOutResponse();
}
