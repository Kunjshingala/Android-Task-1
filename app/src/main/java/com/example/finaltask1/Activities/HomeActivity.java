package com.example.finaltask1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.example.finaltask1.Helper.DBHelper;
import com.example.finaltask1.DataModel.DataModal;
import com.example.finaltask1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<DataModal> userArray = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView headerName, headerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recycle view
        RecyclerView recyclerView = findViewById(R.id.recyclerUser);
        floatingActionButton = findViewById(R.id.floatBtn_addUser);

        // Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        //For Header Hooks
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);

        headerName = header.findViewById(R.id.header_name);
        headerEmail = header.findViewById(R.id.header_email);


        // Enable toolbar
        setSupportActionBar(toolbar);

        // Selected item Highlight
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //get name email and name of user from db for card
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<DataModal> userList = dbHelper.getAllUserDetailsHelper();


        RecyclerContactAdapter adapter = new RecyclerContactAdapter(HomeActivity.this, this, userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.toHome) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.toProfile) {
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else if (id == R.id.toLogout) {
                    Intent intent = new Intent(HomeActivity.this, LogoutActivity.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        // Get Shred Pref.
        SharedPreferences preferences = getSharedPreferences("userLog", MODE_PRIVATE);
        String name = preferences.getString("userName", "**");
        String email = preferences.getString("userEmail", "*@gmail.com");

        headerName.setText(name);
        headerEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        // If back Pressed Close the drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}