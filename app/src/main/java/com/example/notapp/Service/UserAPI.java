package com.example.notapp.Service;

import com.example.notapp.Models.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/api/login")
    Call<UserLoginResponse> loginUser(@Body UserLogin userLogin);
    @POST("/api/register")
    Call<UserRegisterResponse> registerUser(@Body UserLogin userLogin);
}
