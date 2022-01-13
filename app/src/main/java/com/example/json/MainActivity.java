package com.example.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.json.user.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<User> list;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lebavui.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI api = retrofit.create(UserAPI.class);
        Call<List<User>> call = api.getUser();


        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                list = response.body();
                adapter = new Adapter(list, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.setListener(user -> {
                    Intent intent = new Intent(MainActivity.this, ItemDetail.class);
                    intent.putExtra("avatar", user.avatar.photo);
                    intent.putExtra("username", user.username);
                    intent.putExtra("name", user.name);
                    intent.putExtra("email", user.email);
                    intent.putExtra("website", user.website);
                    intent.putExtra("phone", user.phone);
                    intent.putExtra("address",
                            user.company.name + ", ");
                    startActivity(intent);
                });

            };

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
            ;


        });


    }
}

