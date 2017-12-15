package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.utente5academy.provafinale.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Utente on 14/12/2017.
 */

public class Tab1 extends Activity {
private TextView indirizzo;
private TextView codice;
private TextView destinatario;
private TextView giorno;
private FirebaseDatabase database;
private DatabaseReference reference;
private String urlDb=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab1);
        indirizzo=(TextView) findViewById(R.id.indirizzo);
        codice=(TextView)findViewById(R.id.codice);
        destinatario=(TextView) findViewById(R.id.destinatario);
        giorno=(TextView)findViewById(R.id.giorno);
        SharedPreferences preferences=getSharedPreferences("",MODE_PRIVATE);
        String cod=preferences.getString("codice","cod");
        urlDb="https://provafinale-f0d57.firebaseio.com/";
        database=FirebaseDatabase.getInstance();
        reference=database.getReferenceFromUrl(urlDb);

    }
}
