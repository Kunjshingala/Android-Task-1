package com.example.finaltask1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finaltask1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText userName, userEmail, userPassword;

    Button signUpButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.regi_Name);
        userEmail = findViewById(R.id.regi_Email);
        userPassword = findViewById(R.id.regi_Password);

        signUpButton = findViewById(R.id.btn_signUp);

        // Get firebase instance
        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, email, password;

                name = userName.getText().toString();
                email = userEmail.getText().toString();
                password = userPassword.getText().toString();


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter Email Password ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // For register in firebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            SharedPreferences sharedPreferences = getSharedPreferences("userLog", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("userName", name);
                            editor.putString("userEmail", email);
                            editor.apply();

                            Toast.makeText(SignUpActivity.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUpActivity.this, "Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}