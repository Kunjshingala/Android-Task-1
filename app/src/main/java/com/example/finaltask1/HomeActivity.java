package com.example.finaltask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finaltask1.DataModel.CardModal;
import com.example.finaltask1.DataModel.DataModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<DataModal> userArray = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerUser);
        floatingActionButton = findViewById(R.id.floatBtn_addUser);

        //get name email and name of user from db for card
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<DataModal> userList = dbHelper.getAllUserDetailsHelper();


        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}