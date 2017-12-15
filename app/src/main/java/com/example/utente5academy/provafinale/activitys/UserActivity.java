package com.example.utente5academy.provafinale.activitys;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.utente5academy.provafinale.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("deprecation")
public class UserActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle("utente");
        String username = getIntent().getStringExtra("username");
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Pacco");
        spec.setIndicator("Spedizione");
        spec.setContent(new Intent(UserActivity.this, AddPackUser.class));
        tabHost.addTab(spec);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Lista");
        spec.setIndicator("Pacchi");
        spec.setContent(new Intent(UserActivity.this, UserPacks.class));
        tabHost.addTab(spec);



    }
}
