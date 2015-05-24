package com.voxxel.api;

public interface UserService {

    @GET('/api/v1/users')
    User[] users();
}
