package com.example.finaltask1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaltask1.R;
import com.example.finaltask1.Utils.SharedPrefUtils;

public class ProfileActivity extends AppCompatActivity {


    TextView userEmail, userName;
    EditText userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;
    AppCompatButton userSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userEmail = findViewById(R.id.loggedEmail);
        userName = findViewById(R.id.loggedName);
        userContactNumber = findViewById(R.id.loggedContact);
        userGender = findViewById(R.id.loggedGender);
        userCity = findViewById(R.id.loggedCity);
        userCountry = findViewById(R.id.loggedCountry);
        userLanguage = findViewById(R.id.loggedLanguage);
        userSecondryEmail = findViewById(R.id.loggedSecondryEmail);
        userFavCity = findViewById(R.id.loggedFavoriteCity);

        userSaveButton = findViewById(R.id.btn_saveUser);

        // Get Shred Pref.
        SharedPreferences preferences = getSharedPreferences(SharedPrefUtils.prefName, MODE_PRIVATE);

        //set EditText value
        String email = preferences.getString(SharedPrefUtils.KeyEmail, null);
        String name = preferences.getString(SharedPrefUtils.KeyName, null);
        String contact = preferences.getString(SharedPrefUtils.keyContact, null);
        String gender = preferences.getString(SharedPrefUtils.keyGender, null);
        String city = preferences.getString(SharedPrefUtils.keyCity, null);
        String country = preferences.getString(SharedPrefUtils.keyCountry, null);
        String language = preferences.getString(SharedPrefUtils.keyLanguage, null);
        String secondryEmail = preferences.getString(SharedPrefUtils.keySecondryEmail, null);
        String favoriteCity = preferences.getString(SharedPrefUtils.keyFavoriteCity, null);

        userEmail.setText(email);
        userName.setText(name);
        userContactNumber.setText(contact);
        userGender.setText(gender);
        userCity.setText(city);
        userCountry.setText(country);
        userLanguage.setText(language);
        userSecondryEmail.setText(secondryEmail);
        userFavCity.setText(favoriteCity);


        userSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userContactNumber.getText().toString().isEmpty() || userGender.getText().toString().isEmpty() || userCity.getText().toString().isEmpty() || userCountry.getText().toString().isEmpty() || userLanguage.getText().toString().isEmpty() || userSecondryEmail.getText().toString().isEmpty() || userFavCity.getText().toString().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                } else {
                    // For Save
                    SharedPreferences sharedPref = getSharedPreferences(SharedPrefUtils.prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(SharedPrefUtils.keyContact, userContactNumber.getText().toString());
                    editor.putString(SharedPrefUtils.keyGender, userGender.getText().toString());
                    editor.putString(SharedPrefUtils.keyCity, userCity.getText().toString());
                    editor.putString(SharedPrefUtils.keyCountry, userCountry.getText().toString());
                    editor.putString(SharedPrefUtils.keyLanguage, userLanguage.getText().toString());
                    editor.putString(SharedPrefUtils.keySecondryEmail, userSecondryEmail.getText().toString());
                    editor.putString(SharedPrefUtils.keyFavoriteCity, userFavCity.getText().toString());

                    editor.apply();

                    Toast.makeText(ProfileActivity.this, "SharedPref updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}