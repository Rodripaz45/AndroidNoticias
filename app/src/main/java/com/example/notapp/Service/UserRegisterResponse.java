package com.example.notapp.Service;

import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("token")
    public String token;
    @SerializedName("error")
    public String error;
}
