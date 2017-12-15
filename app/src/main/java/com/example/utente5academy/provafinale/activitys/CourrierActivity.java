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
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Pacco");
        spec.setIndicator("Conesgna");
        spec.setContent(new Intent(CourrierActivity.this, Tab1.class));
        tabHost.addTab(spec);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Lista");
        spec2.setIndicator("Pacchi");
        spec2.setContent(new Intent(CourrierActivity.this, Tab1.class));
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Stato");
        spec3.setIndicator("Stato pacco");
        spec3.setContent(new Intent(CourrierActivity.this, Tab1.class));
        tabHost.addTab(spec3);



    }

}
