package com.example.finaltask1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class ManageUserActivity extends AppCompatActivity {

    EditText userEmail, userName, userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;

    ImageButton updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        userContactNumber = findViewById(R.id.user_contactNumber);
        userGender = findViewById(R.id.user_gender);
        userCity = findViewById(R.id.user_city);
        userCountry = findViewById(R.id.user_country);
        userLanguage = findViewById(R.id.user_language);
        userSecondryEmail = findViewById(R.id.user_secondEmail);
        userFavCity = findViewById(R.id.user_favCity);
    }
}