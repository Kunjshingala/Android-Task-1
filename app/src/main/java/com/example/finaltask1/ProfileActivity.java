package com.example.finaltask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {


    TextView userEmail, userName;
    EditText userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;
    AppCompatButton userSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.d("onCreate", "onCreate: called ");

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
        SharedPreferences preferences = getSharedPreferences("userLog", MODE_PRIVATE);
        Log.d("SharedPref", "============> Pref Get");

        //set EditText value
        String email = preferences.getString("userEmail", "**");
        String name = preferences.getString("userName", "*@gmail.com");
        String contact = preferences.getString("contact", "*8751");
        String gender = preferences.getString("gender", "*male");
        String city = preferences.getString("city", "*kasol");
        String country = preferences.getString("country", "*North Korea");
        String language = preferences.getString("language", "*Chinese");
        String secondryEmail = preferences.getString("secondryEmail", "*2@gmail.com");
        String favoriteCity = preferences.getString("favoriteCity", "*panjab");

        Log.d("userDatails", "get values=======>" + email + "  " + name + "  " + contact + "  " + gender + "  " + city + "  " + country + "  " + language + "  " + secondryEmail + "  " + favoriteCity);

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
                // For Save
                SharedPreferences sharedPref = getSharedPreferences("userLog", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("contact", userContactNumber.getText().toString());
                editor.putString("gender", userGender.getText().toString());
                editor.putString("city", userCity.getText().toString());
                editor.putString("country", userCountry.getText().toString());
                editor.putString("language", userLanguage.getText().toString());
                editor.putString("secondryEmail", userSecondryEmail.getText().toString());
                editor.putString("favoriteCity", userFavCity.getText().toString());

                Log.d("userDatails", "set values in pref=======>" + userContactNumber.getText().toString() + "  " + userGender.getText().toString());
                editor.apply();

                String updatedContact = sharedPref.getString("contact", null);
                if (updatedContact == null) {
                    Log.d("updatedContact", "new contact is null");
                } else {
                    Log.d("updatedContact", "new contact is " + updatedContact);
                }

                Toast.makeText(ProfileActivity.this, "SharedPref updated", Toast.LENGTH_SHORT).show();
            }
        });


    }
}