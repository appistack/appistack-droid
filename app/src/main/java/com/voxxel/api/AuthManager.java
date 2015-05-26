package com.voxxel.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AuthManager {

    private static Context context;
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;

    private static final AuthManager instance = new AuthManager();
    private static AccessTokenModel token;

    private AuthManager() {
        token = retrieveToken();
    }

    public static AuthManager getInstance() {
        return instance;
    }

    public static AccessTokenModel getToken() {
        return token;
    }

    public static void setToken(AccessTokenModel newToken) {
        //TODO: store token in SharedPrefs
    }

    public static AccessTokenModel retrieveToken() {
        return null;
    }

    public static void storeToken(AccessTokenModel newToken) {
        token = newToken;
    }

}
