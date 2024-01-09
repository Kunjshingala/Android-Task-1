package com.example.finaltask1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaltask1.R;
import com.example.finaltask1.Utils.SharedPrefUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView userEmail, userName;
    EditText userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.regi_Email);
        userName = findViewById(R.id.regi_Name);

        userContactNumber = findViewById(R.id.regi_Contact);
        userGender = findViewById(R.id.regi_Gender);
        userCity = findViewById(R.id.regi_City);
        userCountry = findViewById(R.id.regi_Country);
        userLanguage = findViewById(R.id.regi_Language);
        userSecondryEmail = findViewById(R.id.regi_SecondryEmail);
        userFavCity = findViewById(R.id.regi_FavoriteCity);

        registerButton = findViewById(R.id.btn_register);

        SharedPreferences sharedPref = getSharedPreferences(SharedPrefUtils.prefName, MODE_PRIVATE);
        String email = sharedPref.getString(SharedPrefUtils.KeyEmail, "null");
        String name = sharedPref.getString(SharedPrefUtils.KeyName, "null");

        userEmail.setText(email);
        userName.setText(name);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact, gender, city, country, language, secondryEmail, favoriteCity;

//                email = userEmail.getText().toString();
//                name = userEmail.getText().toString();
                contact = userContactNumber.getText().toString();
                gender = userGender.getText().toString();
                city = userCity.getText().toString();
                country = userCountry.getText().toString();
                language = userLanguage.getText().toString();
                secondryEmail = userSecondryEmail.getText().toString();
                favoriteCity = userFavCity.getText().toString();

                if (TextUtils.isEmpty(contact) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(city) || TextUtils.isEmpty(country) || TextUtils.isEmpty(language) || TextUtils.isEmpty(secondryEmail) || TextUtils.isEmpty(favoriteCity)) {
                    Toast.makeText(RegisterActivity.this, "Enter all user details", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SharedPreferences preferences = getSharedPreferences(SharedPrefUtils.prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

//                    editor.putString(SharedPrefUtils.KeyEmail, email);
//                    editor.putString(SharedPrefUtils.KeyName, name);
                    editor.putString(SharedPrefUtils.keyContact, contact);
                    editor.putString(SharedPrefUtils.keyGender, gender);
                    editor.putString(SharedPrefUtils.keyCity, city);
                    editor.putString(SharedPrefUtils.keyCountry, country);
                    editor.putString(SharedPrefUtils.keyLanguage, language);
                    editor.putString(SharedPrefUtils.keySecondryEmail, secondryEmail);
                    editor.putString(SharedPrefUtils.keyFavoriteCity, favoriteCity);
                    editor.apply();

                    Toast.makeText(RegisterActivity.this, "Sign Up done", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}