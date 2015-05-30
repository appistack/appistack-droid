package com.voxxel.api;


import com.google.gson.annotations.SerializedName;
import com.voxxel.Constants;

public class SignupModel {
    private String email;
    private String username;
    private String password;

    @SerializedName("password_confirmation")
    private String passwordConfirm;

    @SerializedName("confirm_success_url")
    private String confirmSuccessUrl;

    @SerializedName("config_name")
    private String configName;

    public SignupModel(String email, String username, String password, String passwordConfirm) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.configName = "default";
        this.confirmSuccessUrl = Constants.SIGNUP_CONFIRM_SUCCESS_URL;
    }

    public String getConfirmSuccessUrl() {
        return confirmSuccessUrl;
    }

    public String getConfigName() {
        return configName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
