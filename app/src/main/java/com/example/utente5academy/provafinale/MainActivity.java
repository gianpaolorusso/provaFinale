package com.example.utente5academy.provafinale;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utente5academy.provafinale.activitys.CourrierActivity;
import com.example.utente5academy.provafinale.activitys.RegistrationActivity;
import com.example.utente5academy.provafinale.activitys.UserActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bLog;
    private EditText eUsername;
    private EditText ePassword;
    private CheckBox cbRicorda;
    private Spinner sTipoUtente;
    private String tipo = "";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView registrazion;
    private TextView tTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tTipo = (TextView) findViewById(R.id.txTipo);
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = preferences.edit();
        String utente = preferences.getString("utente", "nessuno");
        tipo = preferences.getString("tipo", "tipo");
        if (utente.equals("nessuno")) {
            registrazion = (TextView) findViewById(R.id.registrazione);
            registrazion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                }
            });
            bLog = (Button) findViewById(R.id.bLog);
            ePassword = (EditText) findViewById(R.id.edPassword);
            eUsername = (EditText) findViewById(R.id.edUsername);
            cbRicorda = (CheckBox) findViewById(R.id.checkbox);
            sTipoUtente = (Spinner) findViewById(R.id.spinner);
            String[] array = {"Corriere", "Utente"};
            ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, array);
            sTipoUtente.setAdapter(adapter);
            bLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipo = sTipoUtente.getSelectedItem().toString();
                    if (cbRicorda.isChecked()) {
                        editor.putString("tipo", tipo);
                        editor.putString("utente", eUsername.getText().toString());
                        editor.commit();
                    }
                    if (eUsername.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Inserire l'username", Toast.LENGTH_SHORT).show();
                    } else if (ePassword.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Inserire la password", Toast.LENGTH_SHORT).show();
                    } else {
                        if (tipo != "tipo") {
                            switching();
                        }
                    }
                }
            });
        } else {
            switching();
        }

    }

    private void switching() {
        switch (tipo) {
            case "Utente": {
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Corriere": {
                Intent i = new Intent(MainActivity.this, CourrierActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    private void verificaDati(final String username, final String passwordUser) {
        String url = "https://provafinale-f0d57.firebaseio.com/" + username + "/Password/";
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReferenceFromUrl(url);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String password = dataSnapshot.getValue().toString();
                if (password.equals(passwordUser)) {
                    Intent i = new Intent(MainActivity.this, UserActivity.class);
                    i.putExtra("username", username);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Dati per il log errati", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
