package com.example.finaltask1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaltask1.R;
import com.example.finaltask1.Utils.SharedPrefUtils;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity {

    TextView userName, userEmail;
    Button logoutUserButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userEmail);
        userEmail = findViewById(R.id.userName);
        logoutUserButton = findViewById(R.id.btn_Logout);

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtils.prefName, MODE_PRIVATE);
        String email = sharedPreferences.getString(SharedPrefUtils.KeyEmail, null);
        String name = sharedPreferences.getString(SharedPrefUtils.KeyName, null);

        userEmail.setText(email);
        userName.setText(name);

        logoutUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(LogoutActivity.this, "Logged out Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogoutActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}