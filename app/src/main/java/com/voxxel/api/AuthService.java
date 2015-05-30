package com.voxxel.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.*;

public interface AuthService {
    @POST("/auth")
    @Headers("Content-Type:application/json")
    UserModel signup(@Body SignupModel signup);

    @POST("/auth/sign_in")
//    @Headers("Content-Type:application/json") //TODO: add SigninModel to implement with JSON
    @FormUrlEncoded
    void signin(@Field("email") String email,
                @Field("password") String password,
                Callback<Response> cb);

    @GET("/auth/validate_token")
    AccessTokenModel validateAccessToken();

    @DELETE("/auth/sign_out")
    BaseResponse signout();
    //TODO: implement callback for signout?
}
