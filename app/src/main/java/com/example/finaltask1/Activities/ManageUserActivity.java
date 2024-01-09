package com.example.finaltask1.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.finaltask1.Helper.DBHelper;
import com.example.finaltask1.R;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {

    EditText userEmail, userName, userContactNumber, userGender, userCity, userCountry, userLanguage, userSecondryEmail, userFavCity;

    ImageButton updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        updateButton = findViewById(R.id.btn_update);
        deleteButton = findViewById(R.id.btn_delete);

        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        userContactNumber = findViewById(R.id.user_contactNumber);
        userGender = findViewById(R.id.user_gender);
        userCity = findViewById(R.id.user_city);
        userCountry = findViewById(R.id.user_country);
        userLanguage = findViewById(R.id.user_language);
        userSecondryEmail = findViewById(R.id.user_secondEmail);
        userFavCity = findViewById(R.id.user_favCity);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<String> userList = dbHelper.getSpecificUserHelper(email);

        if (userList == null) {
            Toast.makeText(getBaseContext(), "Doesn't get user model", Toast.LENGTH_SHORT).show();
        } else {
            userEmail.setText(userList.get(1));
            userName.setText(userList.get(2));
            userContactNumber.setText(userList.get(3));
            userGender.setText(userList.get(4));
            userCity.setText(userList.get(5));
            userCountry.setText(userList.get(6));
            userLanguage.setText(userList.get(7));
            userSecondryEmail.setText(userList.get(8));
            userFavCity.setText(userList.get(9));
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userEmail.getText().toString();
                String name = userName.getText().toString();
                String contact = userContactNumber.getText().toString();
                String gender = userGender.getText().toString();
                String city = userCity.getText().toString();
                String country = userCountry.getText().toString();
                String language = userLanguage.getText().toString();
                String secondryEmail = userSecondryEmail.getText().toString();
                String favoriteCity = userFavCity.getText().toString();


                DBHelper dbHelper = new DBHelper(ManageUserActivity.this);
                boolean isUpdate = dbHelper.updateUserHelper(email, name, contact, gender, city, country, language, secondryEmail, favoriteCity);

                if (isUpdate) {
                    Toast.makeText(getBaseContext(), "Values updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManageUserActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUserActivity.this);
                builder.setTitle("Delete Profile");
                builder.setMessage("Are you sure to delete Profile ?");

                // Click YES
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String email = userEmail.getText().toString();
                        DBHelper dbHelper = new DBHelper(ManageUserActivity.this);
                        boolean isDelete = dbHelper.deleteUserHelper(email);
                        if (isDelete) {
                            Toast.makeText(ManageUserActivity.this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ManageUserActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ManageUserActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Click No
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to do
                    }
                });

                //Render AlertDialog
                builder.show();
            }
        });
    }
}