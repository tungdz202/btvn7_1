package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ImageView avatar = findViewById(R.id.avatar);
        TextView username =  findViewById(R.id.username);
        TextView email =  findViewById(R.id.email);
        TextView address =  findViewById(R.id.address);
        TextView phone =  findViewById(R.id.phone);
        TextView company =  findViewById(R.id.company);

        String getAvatar = getIntent().getStringExtra("avatar");
        String getUsername = getIntent().getStringExtra("username");
        String getName = getIntent().getStringExtra("name");
        String getEmail = getIntent().getStringExtra("email");
        String getAddress = getIntent().getStringExtra("avatar");
        String getPhone = getIntent().getStringExtra("avatar");

    }
}