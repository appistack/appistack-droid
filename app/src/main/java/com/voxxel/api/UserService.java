package com.voxxel.api;

import java.util.List;
import retrofit.http.*;

public interface UserService {
    @GET("/users")
    List<User> getUser();

    @GET("/users/{id}")
    User getUser(@Path("id") int id);
}
