package com.voxxel.api;

import com.google.gson.annotations.SerializedName;

public class AccessTokenModel extends BaseResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type") //Bearer
    private String tokenType;
    private String expiry;
    private String client;
    private String uid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        accessToken = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String type) {
        tokenType = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String _uid) {
        uid = _uid;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String _client) {
        client = _client;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String _expiry) {
        expiry = _expiry;
    }

    @Override
    public String toString() {
//        if (super.getError() != null) {
//            return "AccessTokenModel{error='" + super.getError() + "'}";
//        }

        return "AccessTokenModel{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", uid='" + uid + '\'' +
                ", client='" + client + '\'' +
                ", expiry=" + expiry +
                '}';
    }
}
