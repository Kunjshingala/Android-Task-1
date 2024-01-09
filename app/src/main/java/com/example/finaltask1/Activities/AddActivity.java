package com.example.finaltask1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finaltask1.Helper.DBHelper;
import com.example.finaltask1.R;

public class AddActivity extends AppCompatActivity {

    EditText userEmail, userName, userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;

    AppCompatButton addUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add user");

        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        userContactNumber = findViewById(R.id.user_contactNumber);
        userGender = findViewById(R.id.user_gender);
        userCity = findViewById(R.id.user_city);
        userCountry = findViewById(R.id.user_country);
        userLanguage = findViewById(R.id.user_language);
        userSecondryEmail = findViewById(R.id.user_secondEmail);
        userFavCity = findViewById(R.id.user_favCity);

        addUserButton = findViewById(R.id.btn_addUser);
        DBHelper dbHelper = new DBHelper(this);

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String name = userName.getText().toString();
                String contact = userContactNumber.getText().toString();
                String gender = userGender.getText().toString();
                String city = userCity.getText().toString();
                String country = userCountry.getText().toString();
                String language = userLanguage.getText().toString();
                String secondaryEmail = userSecondryEmail.getText().toString();
                String favoriteCity = userFavCity.getText().toString();

                boolean isAdd = dbHelper.addUser(email, name, contact, gender, city, country, language, secondaryEmail, favoriteCity);
//
                if (isAdd) {
                    Toast.makeText(AddActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddActivity.this, "Something goes wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}