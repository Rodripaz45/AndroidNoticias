package com.example.notapp.Service;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("token")
    public String token;
    @SerializedName("error")
    public String error;
}
