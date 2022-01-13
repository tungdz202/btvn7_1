package com.example.json;

import com.example.json.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {
    @GET("/jsons/users.json")
    Call<List<User>> getUser();

}
