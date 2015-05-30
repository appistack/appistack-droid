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

    private static final AuthManager instance = new AuthManager();
    private static AccessTokenModel token;
    private String tokenKey = "token";
    private String tokenTypeKey = "token_type";
    private String tokenClientKey = "token_client";
    private String tokenExpiryKey = "token_expiry";
    private String tokenUidKey = "token_uid";

    private AuthManager() {
        token = null;
    }

    public static AuthManager getInstance() {
        return instance;
    }

    public void setContext(Context baseContext) {
        context = baseContext;
        prefs = context.getSharedPreferences(ACCESS_TOKEN, Activity.MODE_PRIVATE);
    }

    public void setToken(AccessTokenModel newToken) {
        token = newToken;
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(tokenKey, token.getAccessToken()).apply();
        prefsEditor.putString(tokenTypeKey, token.getTokenType()).apply();
        prefsEditor.putString(tokenClientKey, token.getClient()).apply();
        prefsEditor.putString(tokenExpiryKey, token.getExpiry()).apply();
        prefsEditor.putString(tokenUidKey, token.getUid()).apply();
    }

    public AccessTokenModel retrieveToken() {
        token = new AccessTokenModel();
        token.setAccessToken(prefs.getString(tokenKey, ""));
        token.setTokenType(prefs.getString(tokenTypeKey, ""));
        token.setClient(prefs.getString(tokenClientKey, ""));
        token.setExpiry(prefs.getString(tokenExpiryKey, ""));
        token.setUid(prefs.getString(tokenUidKey, ""));

        return token;
    }

    public void storeToken(AccessTokenModel newToken) {
        //TODO: store token in SharedPrefs
    }
}
