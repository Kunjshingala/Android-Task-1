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

        SharedPreferences sharedPref = getSharedPreferences("userLog", MODE_PRIVATE);
        String email = sharedPref.getString("userEmail", "null");
        String name = sharedPref.getString("userName", "null");

        userEmail.setText(email);
        userName.setText(name);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, name, contact, gender, city, country, language, secondryEmail, favoriteCity;

                email = userEmail.getText().toString();
                name = userEmail.getText().toString();
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
                    SharedPreferences preferences = getSharedPreferences("userLog", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("userEmail", email);
                    editor.putString("userName", name);
                    editor.putString("contact", contact);
                    editor.putString("gender", gender);
                    editor.putString("city", city);
                    editor.putString("country", country);
                    editor.putString("language", language);
                    editor.putString("secondryEmail", secondryEmail);
                    editor.putString("favoriteCity", favoriteCity);
                    editor.apply();

                    String method = "Id";
                    writeSharedPreferences(method, email, name);

                    Toast.makeText(RegisterActivity.this, "Sign Up done", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void writeSharedPreferences(String loginMethod, String email, String name) {

        SharedPreferences sharedPref = getSharedPreferences("userLog", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("LoginMethod", loginMethod);
        editor.putString("userEmail", email);
        editor.putString("userName", name);

        editor.apply();

        String loginmethodcheck = sharedPref.getString("LoginMethod", "null");
        Log.d("Check", "Login method which save is: " + loginmethodcheck);
    }

}