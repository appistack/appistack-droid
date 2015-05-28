package com.voxxel.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class AuthManager {

    private Context context;
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;

    private static final AuthManager instance = new AuthManager();
    private static AccessTokenModel token;
    private String tokenKey = "token";
    private String tokenTypeKey = "token_type";

    private AuthManager() {
        token = null;
    }

    public static AuthManager getInstance() {
        return instance;
    }

    public static AccessTokenModel getToken() {
        return token;
    }

    public void setContext(Context baseContext) {
        context = baseContext;
        prefs = context.getSharedPreferences(ACCESS_TOKEN, Activity.MODE_PRIVATE);
        prefsEditor = prefs.edit();
    }

    public void setToken(AccessTokenModel newToken) {
        token = newToken;
        prefsEditor.putString(tokenKey, token.getAccessToken()).commit();
        prefsEditor.putString(tokenTypeKey, token.getTokenType()).commit();
        Log.i("AuthManager", token.toString());
        Log.i("AuthManager", prefs.getString(tokenKey, ""));
        Log.i("AuthManager", prefs.getString(tokenTypeKey, ""));
    }

    public AccessTokenModel retrieveToken() {
        token = new AccessTokenModel();
        token.setAccessToken(prefs.getString(tokenKey, ""));
        token.setTokenType(prefs.getString(tokenTypeKey, ""));
        return token;
    }

    public void storeToken(AccessTokenModel newToken) {
        //TODO: store token in SharedPrefs
    }
}
