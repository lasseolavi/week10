package com.example.week10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        UserStorage.getInstance().loadUsers(context);

    }
    public void switchToUser(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void switchToUserStorage(View view){
        UserStorage us = UserStorage.getInstance();
        ArrayList userList = us.getUsers();
        UserListAdapter ula = new UserListAdapter(context, userList);
        ula.sortUsers(userList);
        Intent intent = new Intent(this, UserStorageActivity.class);
        startActivity(intent);
    }
}