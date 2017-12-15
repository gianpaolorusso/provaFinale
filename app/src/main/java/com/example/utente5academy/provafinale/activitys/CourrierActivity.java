package com.example.utente5academy.provafinale.activitys;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.utente5academy.provafinale.R;

/**
 * Created by utente5.academy on 15/12/2017.
 */
@SuppressWarnings("deprecation")
public class CourrierActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courrier);
        String user=getIntent().getStringExtra("username");
        String tipo=getIntent().getStringExtra("tipo");
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Pacco");
        spec.setIndicator("IN ATTESA");
        spec.setContent(new Intent(CourrierActivity.this, Tab1.class).putExtra("username",user).putExtra("tipo",tipo));
        tabHost.addTab(spec);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Pacco");
        spec2.setIndicator("IN CONSEGNA");
        spec2.setContent(new Intent(CourrierActivity.this, Tab2.class).putExtra("username",user));
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Pacco");
        spec3.setIndicator("INFO PACCO");
        spec3.setContent(new Intent(CourrierActivity.this, Tab3.class).putExtra("username",user));
        tabHost.addTab(spec3);




    }

}
