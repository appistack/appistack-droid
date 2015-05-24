package com.voxxel.api;

import retrofit.http.*;

public interface UserService {
    @GET("/api/v1/users")
    User[] users();
}
