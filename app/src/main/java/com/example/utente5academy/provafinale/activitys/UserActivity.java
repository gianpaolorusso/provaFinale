package com.example.utente5academy.provafinale.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utente5academy.provafinale.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {
   private FirebaseDatabase database ;
   private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle("utente");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        String username=getIntent().getStringExtra("username");


    }
}
