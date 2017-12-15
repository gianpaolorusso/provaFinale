package com.example.utente5academy.provafinale;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utente5academy.provafinale.activitys.CourrierActivity;
import com.example.utente5academy.provafinale.activitys.RegistrationActivity;
import com.example.utente5academy.provafinale.activitys.UserActivity;
import com.example.utente5academy.provafinale.classe.Rest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Button bLog;
    private EditText eUsername;
    private EditText ePassword;
    private CheckBox cbRicorda;
    private Spinner sTipoUtente;
    private AsyncHttpClient client;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView registrazion;
    private TextView tTipo;
    private Rest rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tTipo = (TextView) findViewById(R.id.txTipo);
        rest = new Rest(getApplicationContext());
        client = new AsyncHttpClient();
        bLog = (Button) findViewById(R.id.bLog);
        ePassword = (EditText) findViewById(R.id.edPassword);
        eUsername = (EditText) findViewById(R.id.edUsername);
        cbRicorda = (CheckBox) findViewById(R.id.check);
        sTipoUtente = (Spinner) findViewById(R.id.spinner);
        String[] array = {"Corriere", "Utente"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, array);
        sTipoUtente.setAdapter(adapter);
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = preferences.edit();
        String user = preferences.getString("utente", "nessuno");
        String pass = preferences.getString("password", "nessuno");
       final String tipo = preferences.getString("tipo", "tipo");
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

        bLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ut=sTipoUtente.getSelectedItem().toString();
                if (cbRicorda.isChecked()) {
                    editor.putString("tipo",ut);
                    editor.putString("utente", eUsername.getText().toString());
                    editor.putString("password", ePassword.getText().toString());
                    editor.commit();
                    editor.apply();
                }
                if (eUsername.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Inserire l'username", Toast.LENGTH_SHORT).show();
                }
                if (ePassword.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Inserire la password", Toast.LENGTH_SHORT).show();
                } else {
                    LogIn(eUsername.getText().toString(), ePassword.getText().toString(), ut);
                }
            }
        });
        if (user.equals("nessuno")) {


        } else {
            switch (tipo) {
                case "Utente":
                    Intent i = new Intent(MainActivity.this, UserActivity.class);
                    i.putExtra("username", user);
                    i.putExtra("tipo", tipo);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();

                    };

                break;
                case "Corriere":{
                    i = new Intent(MainActivity.this, CourrierActivity.class);
                    i.putExtra("username", user);
                    i.putExtra("tipo", tipo);
                    pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();

                    }}
                ;
                break;
            }

        }
    }

    public void LogIn(final String username, final String pass, final String tipo) {
        String url = "https://provafinale-f0d57.firebaseio.com/Users/" + tipo + "/" + username + ".json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject jsonObject = null;
                String objReceived = new String(responseBody);
                try {
                    jsonObject = new JSONObject(objReceived);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject != null) {
                    try {
                        if (jsonObject.get("Password").equals(pass)) {
                            switch (tipo) {
                                case "Utente": {
                                    Intent i = new Intent(MainActivity.this, UserActivity.class);
                                    i.putExtra("username", username);
                                    i.putExtra("tipo", tipo);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                                    try {
                                        pendingIntent.send();
                                    } catch (PendingIntent.CanceledException e) {
                                        e.printStackTrace();

                                    }
                                }
                                ;
                                break;
                                case "Corriere": {
                                    Intent i = new Intent(MainActivity.this, CourrierActivity.class);
                                    i.putExtra("username", username);
                                    i.putExtra("tipo", tipo);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                                    try {
                                        pendingIntent.send();
                                    } catch (PendingIntent.CanceledException e) {
                                        e.printStackTrace();

                                    }
                                }
                                ;
                                break;
                            }


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

            }
        });
    }
}

