package com.example.utente5academy.provafinale.activitys;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.utente5academy.provafinale.R;

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
        spec.setIndicator("+Pacco");
        spec.setContent(new Intent(UserActivity.this, AddPackUser.class));
        tabHost.addTab(spec);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Lista");
        spec.setIndicator("Pacchi");
        spec.setContent(new Intent(UserActivity.this, UserPacks.class));
        tabHost.addTab(spec);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Stato Spedizione");
        spec.setIndicator("Pacchi");
        spec.setContent(new Intent(UserActivity.this,StatoSpedizione.class));
        tabHost.addTab(spec);



    }
}
