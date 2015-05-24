package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface UserService {
    @GET("/users")
    List<UserModel> getUser();

    @GET("/users/{id}")
    UserModel getUser(@Path("id") int id);
}
