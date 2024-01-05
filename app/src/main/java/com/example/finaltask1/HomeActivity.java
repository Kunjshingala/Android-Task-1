package com.example.finaltask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finaltask1.DataModel.CardModal;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<CardModal> userArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerContact);

        userArray.add(new CardModal("Kunj", "kunjshingala.p@gmail.com"));
        userArray.add(new CardModal("Ram", "ram@gmail.com"));
        userArray.add(new CardModal("Shyam", "shyam@gmail.com"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, userArray);
        recyclerView.setAdapter(adapter);
    }
}