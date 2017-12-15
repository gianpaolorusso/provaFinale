package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.utente5academy.provafinale.R;

import java.util.concurrent.TimeoutException;

public class Tab2 extends Activity {
private String codice;
private TextView indirizzo;
private TextView giorno;
private TextView destinatario;
private TextView cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        codice=getIntent().getStringExtra("codicepacco");
        cod=(TextView)findViewById(R.id.cod);
        cod.setText(codice);
        indirizzo=(TextView)findViewById(R.id.indirizzo);
        giorno=(TextView)findViewById(R.id.giorno);
        destinatario=(TextView)findViewById(R.id.destinatario);
        


    }
}
